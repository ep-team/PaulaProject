package com.eshop.controller.portal;

import com.alipay.api.internal.util.StringUtils;
import com.eshop.common.Const;
import com.eshop.common.ResponseCode;
import com.eshop.common.ServerResponse;
import com.eshop.pojo.User;
import com.eshop.service.ICartService;
import com.eshop.utilities.CookieUtil;
import com.eshop.utilities.JsonUtil;
import com.eshop.utilities.RedisPoolUtil;
import com.eshop.vo.CartVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/")
public class CartController {

	private static Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private ICartService iCartService;

    /*
     * 查询购物车
     */
    @RequestMapping("list.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse<CartVo> list(HttpSession session){
    public ServerResponse<CartVo> list(HttpServletRequest httpServletRequest){
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
        return iCartService.list(user.getId());
    }

    /*
     * 添加商品到购物车
     */
    @RequestMapping("add.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse<CartVo> add(HttpSession session, Integer count, Integer productId){
    public ServerResponse<CartVo> add(HttpServletRequest httpServletRequest, Integer count, Integer productId){
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
        return iCartService.add(user.getId(),productId,count);
    }


    /*
     * 更新购物车,即更新购物车中商品的的数量
     */
    @RequestMapping("update.do")
    @ResponseBody
    //二期修改-start
    // ServerResponse<CartVo> update(HttpSession session, Integer count, Integer productId){
    ServerResponse<CartVo> update(HttpServletRequest httpServletRequest, Integer count, Integer productId){
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
        return iCartService.update(user.getId(),productId,count);
    }

    /*
     * 删除购物车中的商品
     */
    @RequestMapping("delete_product.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse<CartVo> deleteProduct(HttpSession session,String productIds){
    public ServerResponse<CartVo> deleteProduct(HttpServletRequest httpServletRequest,String productIds){
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
        return iCartService.deleteProduct(user.getId(),productIds);
    }

    /*
     * 全选购物车中的所有商品
     */
    @RequestMapping("select_all.do")
    @ResponseBody
    //二期修改-end
    //public ServerResponse<CartVo> selectAll(HttpSession session){
    public ServerResponse<CartVo> selectAll(HttpServletRequest httpServletRequest){
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
        return iCartService.selectOrUnSelect(user.getId(),null,Const.Cart.CHECKED);
    }

    /*
     * 取消勾选购物车中的所有商品
     */
    @RequestMapping("un_select_all.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse<CartVo> unSelectAll(HttpSession session){
    public ServerResponse<CartVo> unSelectAll(HttpServletRequest httpServletRequest){
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
        return iCartService.selectOrUnSelect(user.getId(),null,Const.Cart.UN_CHECKED);
    }


    /*
     * 单独选购物车中的某个商品
     */
    @RequestMapping("select.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse<CartVo> select(HttpSession session,Integer productId){
    public ServerResponse<CartVo> select(HttpServletRequest httpServletRequest,Integer productId){
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
        return iCartService.selectOrUnSelect(user.getId(),productId,Const.Cart.CHECKED);
    }

    /*
     * 单独反选购物车中某个商品
     */
    @RequestMapping("un_select.do")
    @ResponseBody
    //二期修改-start
    // ServerResponse<CartVo> unSelect(HttpSession session,Integer productId){
    ServerResponse<CartVo> unSelect(HttpServletRequest httpServletRequest,Integer productId){
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
        return iCartService.selectOrUnSelect(user.getId(),productId,Const.Cart.UN_CHECKED);
    }


    /*
     * 查询当前用户的购物车里面的产品数量,如果产品a有10个,产品b有20个,那么数量就是30.
     * 用于显示在页面右上角的购物车上
     */
    @RequestMapping("get_cart_product_count.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse<Integer> getCartProductCount(HttpSession session){
    public ServerResponse<Integer> getCartProductCount(HttpServletRequest httpServletRequest){
    	//User user = (User)session.getAttribute(Const.CURRENT_USER);
    	String loginToken = CookieUtil.readLoginToken(httpServletRequest);
		if(StringUtils.isEmpty(loginToken)) {
			return ServerResponse.createByErrorMessage("用户未登录, 无法获取当前用户的信息");
		}
		String userJsonStr = RedisPoolUtil.get(loginToken);
		User user = JsonUtil.string2Obj(userJsonStr, User.class);		
     //二期修改-end
        if(user ==null){
            return ServerResponse.createBySuccess(0);
        }
        return iCartService.getCartProductCount(user.getId());
    }





}
