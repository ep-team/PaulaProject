package com.eshop.controller.backend;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.common.Const;
import com.eshop.common.ServerResponse;
import com.eshop.pojo.User;
import com.eshop.service.IUserService;
import com.eshop.utilities.CookieUtil;
import com.eshop.utilities.JsonUtil;
import com.eshop.utilities.RedisShardedPoolUtil;

//后台用户管理
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
	
	private static Logger logger = LoggerFactory.getLogger(UserManageController.class);
	@Autowired
	private IUserService iUserService;
	
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	@ResponseBody
	//admin005/admin005
	//二期 start
	//public ServerResponse<User> login(String username, String password, HttpSession session) {
	public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
	//二期 end
		ServerResponse response = iUserService.login(username, password);
		if(response.isSuccess()) {
			User user = (User) response.getData();
			if(user.getRole() == Const.Role.ROLE_ADMIN) {
				//二期 start
				//说明登录的是管理员
				//session.setAttribute(Const.CURRENT_USER, user);
				
				//新增redis共享cookie，session的方式
                CookieUtil.writeLoginToken(httpServletResponse,session.getId());
                RedisShardedPoolUtil.setEx(session.getId(), Const.RedisCacheExtime.REDIS_SESSION_EXTIME, JsonUtil.obj2String(response.getData()));
                //二期 end
			}else {
				return ServerResponse.createByErrorMessage("不是管理员,无法登录");
			}
		}
		return response;
	}
}
