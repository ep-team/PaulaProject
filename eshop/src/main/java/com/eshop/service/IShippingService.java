package com.eshop.service;

import com.eshop.common.ServerResponse;
import com.eshop.pojo.Shipping;
import com.github.pagehelper.PageInfo;

/**
 * Created by Paula
 */
public interface IShippingService {
	/**
	 * 
	 * @param userId
	 * @param shipping
	 * @return
	 */
    ServerResponse add(Integer userId, Shipping shipping);
    
    /**
     * 
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<String> del(Integer userId,Integer shippingId);
    
    /**
     * 
     * @param userId
     * @param shipping
     * @return
     */
    ServerResponse update(Integer userId, Shipping shipping);
    
    /**
     * 
     * @param userId
     * @param shippingId
     * @return
     */
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);
    
    /**
     * 
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

}
