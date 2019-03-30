package com.eshop.controller.portal;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.demo.trade.config.Configs;
import com.eshop.common.Const;
import com.eshop.common.ResponseCode;
import com.eshop.common.ServerResponse;
import com.eshop.pojo.User;
import com.eshop.service.IOrderService;
import com.eshop.utilities.CookieUtil;
import com.eshop.utilities.JsonUtil;
import com.eshop.utilities.RedisPoolUtil;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;


@Controller
@RequestMapping("/order/")
public class OrderController {

    private static  final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService iOrderService;


    @RequestMapping("create.do")
    @ResponseBody
  //二期修改-start
    //public ServerResponse create(HttpSession session, Integer shippingId){
    public ServerResponse create(HttpServletRequest httpServletRequest, Integer shippingId){
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
        return iOrderService.createOrder(user.getId(),shippingId);
    }


    @RequestMapping("cancel.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse cancel(HttpSession session, Long orderNo){
    public ServerResponse cancel(HttpServletRequest httpServletRequest, Long orderNo){
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
        return iOrderService.cancel(user.getId(),orderNo);
    }


    @RequestMapping("get_order_cart_product.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse getOrderCartProduct(HttpSession session){
    public ServerResponse getOrderCartProduct(HttpServletRequest httpServletRequest){
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
        return iOrderService.getOrderCartProduct(user.getId());
    }



    @RequestMapping("detail.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse detail(HttpSession session,Long orderNo){
    public ServerResponse detail(HttpServletRequest httpServletRequest,Long orderNo){
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
        return iOrderService.getOrderDetail(user.getId(),orderNo);
    }

    @RequestMapping("list.do")
    @ResponseBody
    //二期修改-start
    //public ServerResponse list(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
    public ServerResponse list(HttpServletRequest httpServletRequest, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
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
        return iOrderService.getOrderList(user.getId(),pageNum,pageSize);
    }























    /*
     * 支付功能
     * 为订单号orderNo支付
     */
    @RequestMapping("pay.do")
    @ResponseBody
  //二期修改-start
    //public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request){
    public ServerResponse pay(HttpServletRequest httpServletRequest, Long orderNo, HttpServletRequest request){
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
        
        //获取upload文件夹的路径 ,将该路径的下的二维码上传到ftp服务器上,然后返回给前端该二维码图片的地址,然后前端把二维码图片进行展示,让买家扫码支付
        String path = request.getSession().getServletContext().getRealPath("upload");
        return iOrderService.pay(orderNo,user.getId(),path);
    }

    /*
     * 支付宝的回调函数
     * 返回值类型为Object为支付宝要求的
     * 传参是HttpServletRequest request, 支付宝的参数会放到HttpServletRequest中供我们获取
     */
    @RequestMapping("alipay_callback.do")
    @ResponseBody
    public Object alipayCallback(HttpServletRequest request){
        Map<String,String> params = Maps.newHashMap();
        //requestParams<String, String[]>
        //requestParams转化为params->Map<String, String>
        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator();iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0 ; i <values.length;i++){

                valueStr = (i == values.length -1)?valueStr + values[i]:valueStr + values[i]+",";
            }
            params.put(name,valueStr);
        }
        //sign为签名
        logger.info("支付宝回调,sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());

        //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.
        //通过调用AlipaySignature.rsaCheckV2()可以验证回调的正确性

        params.remove("sign_type");
        try {
        	//AlipaySignature.rsaCheckV2的传参分别为Map<String, String>, 支付宝的publicKey而不是商家的publicKey,字符集, signType为RSA2
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());

            if(!alipayRSACheckedV2){
                return ServerResponse.createByErrorMessage("非法请求,验证不通过,再恶意请求我就报警找网警了");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝验证回调异常",e);
        }

        //TODO 验证各种数据


        //
        ServerResponse serverResponse = iOrderService.aliCallback(params);
        if(serverResponse.isSuccess()){
            return Const.AlipayCallback.RESPONSE_SUCCESS;
        }
        return Const.AlipayCallback.RESPONSE_FAILED;
    }

    /*
     * 买家扫码付款成功后,前台会调用该接口,查看是否付款成功
     */
    @RequestMapping("query_order_pay_status.do")
    @ResponseBody
  //二期修改-start
    //public ServerResponse<Boolean> queryOrderPayStatus(HttpSession session, Long orderNo){
    public ServerResponse<Boolean> queryOrderPayStatus(HttpServletRequest httpServletRequest, Long orderNo){
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

        ServerResponse serverResponse = iOrderService.queryOrderPayStatus(user.getId(),orderNo);
        if(serverResponse.isSuccess()){
            return ServerResponse.createBySuccess(true);
        }
        return ServerResponse.createBySuccess(false);
    }



















}
