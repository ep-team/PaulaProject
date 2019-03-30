package com.eshop.controller.portal;

import com.alipay.api.internal.util.StringUtils;
import com.eshop.common.Const;
import com.eshop.common.ResponseCode;
import com.eshop.common.ServerResponse;
import com.eshop.pojo.Shipping;
import com.eshop.pojo.User;
import com.eshop.service.IShippingService;
import com.eshop.utilities.CookieUtil;
import com.eshop.utilities.JsonUtil;
import com.eshop.utilities.RedisPoolUtil;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/shipping/")
public class ShippingController {

	private static Logger logger = LoggerFactory.getLogger(ShippingController.class);
	
    @Autowired
    private IShippingService iShippingService;


    @RequestMapping("add.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse add(HttpSession session,Shipping shipping){
    public ServerResponse add(HttpServletRequest httpServletRequest,Shipping shipping){
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
    	String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.string2Obj(userJsonStr, User.class);		
     //二期修改-end
		
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.add(user.getId(),shipping);
    }


    @RequestMapping("del.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse del(HttpSession session,Integer shippingId){
    public ServerResponse del(HttpServletRequest httpServletRequest,Integer shippingId){
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
    	String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.string2Obj(userJsonStr, User.class);		
     //二期修改-end
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.del(user.getId(),shippingId);
    }

    @RequestMapping("update.do")
    @ResponseBody
  //二期修改-start
    //public ServerResponse update(HttpSession session,Shipping shipping){
    public ServerResponse update(HttpServletRequest httpServletRequest,Shipping shipping){
    
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
    	String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.string2Obj(userJsonStr, User.class);		
     //二期修改-end
		
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.update(user.getId(),shipping);
    }


    @RequestMapping("select.do")
    @ResponseBody
  //二期修改-start
    //public ServerResponse<Shipping> select(HttpSession session,Integer shippingId){
    public ServerResponse<Shipping> select(HttpServletRequest httpServletRequest,Integer shippingId){
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
    	String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.string2Obj(userJsonStr, User.class);		
     //二期修改-end
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.select(user.getId(),shippingId);
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
    //二期修改-start 
      //                                 HttpSession session){
                                         HttpServletRequest httpServletRequest){
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.string2Obj(userJsonStr, User.class);		
     //二期修改-end
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.list(user.getId(),pageNum,pageSize);
    }














}
