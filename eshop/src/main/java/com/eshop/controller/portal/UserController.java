package com.eshop.controller.portal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.internal.util.StringUtils;
import com.eshop.common.Const;
import com.eshop.common.ResponseCode;
import com.eshop.common.ServerResponse;
import com.eshop.dao.UserMapper;
import com.eshop.pojo.User;
import com.eshop.service.IUserService;
import com.eshop.utilities.CookieUtil;
import com.eshop.utilities.JsonUtil;
import com.eshop.utilities.RedisPoolUtil;


@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService iUserService;
	/*
	 * User login
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="login.do", method = RequestMethod.POST)
	@ResponseBody//返回时自动通过SpringMVC的json插件将返回值序列化为json,
				 //如在springmvc-dispatcher-servlet.xml配置了json相关的converter
				 //并注入属性值supportedMediaType为application/json
	//二期修改-start
	//public ServerResponse<User> login(String username, String password, HttpSession session) {
	public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
	//二期修改-end
		//serveice -> MyBatis -> dao 
		ServerResponse<User> response = iUserService.login(username, password);
		if(response.isSuccess()) {
			
			//二期修改-start
			
			//session.setAttribute(Const.CURRENT_USER, response.getData());
			CookieUtil.writeLoginToken(httpServletResponse, session.getId());
			
			//登录成功, 不将user信息放入session中, 直接放入redis
			//sessionId在chrom->Network->Request Headers中->cookie中的JSESSIONID, 在chrom->Application->JSESSIONID也是
			//tomcat重启, 再次请求时, request的JSESSIONID会改变
			RedisPoolUtil.setEx(session.getId(), Const.RedisCacheExtime.REDIS_SESSION_EXTIME, JsonUtil.obj2String(response.getData()));
		    
			//二期修改-end
		}
		return response;
	}
	
	@RequestMapping(value="logout.do", method = RequestMethod.POST)
	@ResponseBody
	
	//二期修改-start
	//public ServerResponse<String> logout(HttpSession session) {
	public ServerResponse<String> logout(HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		//session.removeAttribute(Const.CURRENT_USER);
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		CookieUtil.delLoginToken(httpServletRequest, httpServletResponse);
		RedisPoolUtil.del(loginToken);
    //二期修改-end
		
		return ServerResponse.createBySuccess();
	}
	
	@RequestMapping(value="register.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> reigster(User user) {
		return iUserService.register(user);
	}
	
	@RequestMapping(value="check_valid.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> checkValid(String str, String type){
		return iUserService.checkValid(str, type);
	}
	
	@RequestMapping(value="get_user_info.do", method = RequestMethod.POST)
	@ResponseBody
	//二期修改-start
	//public ServerResponse<User> getUserInfo(HttpSession session) {
	public ServerResponse<User> getUserInfo(HttpServletRequest httpServletRequest) {
		//User user = (User) session.getAttribute(Const.CURRENT_USER);
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.string2Obj(userJsonStr, User.class);
		
		//二期修改-end
		if(user != null) {
			return ServerResponse.createBySuccess(user);
		}
		return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
	}
	
	@RequestMapping(value="forget_get_question.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> forgetPasswordGetQuestion(String username) {
		return iUserService.selectQuestion(username);
	}
	
	@RequestMapping(value="forget_check_answer.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
		//使用本地的Guava缓存来做token
		return iUserService.checkAnswer(username, question, answer);
	}
	
	@RequestMapping(value="forget_reset_password.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
		return iUserService.forgetResetPassword(username, passwordNew, forgetToken);
	}
	
	@RequestMapping(value="reset_password.do", method = RequestMethod.POST)
	@ResponseBody
	//二期修改-start
	//public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
	public ServerResponse<String> resetPassword(HttpServletRequest httpServletRequest, String passwordOld, String passwordNew) {
		//User user = (User)session.getAttribute(Const.CURRENT_USER);
				String loginToken = CookieUtil.readLoginToken(httpServletRequest);
				if(StringUtils.isEmpty(loginToken)) {
					return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
				}
				String userJsonStr = RedisPoolUtil.get(loginToken);
				User user = JsonUtil.string2Obj(userJsonStr, User.class);
				
	//二期修改-end
				
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return iUserService.resetPassword(passwordOld, passwordNew, user);
	}
	
	@RequestMapping(value="update_infomation.do", method = RequestMethod.POST)
	@ResponseBody
	//二期修改-start
	//public ServerResponse<User> update_infomation(HttpSession session, User user) {
	public ServerResponse<User> update_infomation(HttpServletRequest httpServletRequest, User user) {
		//User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User currentUser = JsonUtil.string2Obj(userJsonStr, User.class);		
    //二期修改-end
		
		if(currentUser == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		currentUser.setId(currentUser.getId());
		currentUser.setUsername(currentUser.getUsername());
		ServerResponse<User> response = iUserService.updateInfomation(currentUser);
		if(response.isSuccess()) {
			//二期修改-start
			//session.setAttribute(Const.CURRENT_USER, response.getData());
			RedisPoolUtil.setEx(loginToken, Const.RedisCacheExtime.REDIS_SESSION_EXTIME, JsonUtil.obj2String(response.getData()));
			//二期修改-end
		}
		return response;
	}
	
	@RequestMapping(value="get_infomation.do", method = RequestMethod.POST)
	@ResponseBody
	//二期修改-start
	//public ServerResponse<User> get_infomation(HttpSession session){
	public ServerResponse<User> get_infomation(HttpServletRequest httpServletRequest){
		//User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User currentUser = JsonUtil.string2Obj(userJsonStr, User.class);		
    //二期修改-end
		
		if(currentUser == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,需要强制登录status=10");
		}
		return iUserService.getInfomation(currentUser.getId());
	}
}
