package com.eshop.service;

import com.eshop.common.ServerResponse;
import com.eshop.vo.CartVo;

/**
 * Created by Paula
 */
public interface ICartService {
	
	/**
	 * 
	 * @param userId
	 * @param productId
	 * @param count
	 * @return
	 */
    ServerResponse<CartVo> addProduct(Integer userId, Integer productId, Integer count);
    
    /**
     * 
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> updateProduct(Integer userId,Integer productId,Integer count);
    
    /**
     * 
     * @param userId
     * @param productIds
     * @return
     */
    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);
    
    /**
     * 
     * @param userId
     * @return
     */
    ServerResponse<CartVo> listCart (Integer userId);
    
    /**
     * 
     * @param userId
     * @param productId
     * @param checked
     * @return
     */
    ServerResponse<CartVo> selectOrUnSelectProduct (Integer userId,Integer productId,Integer checked);
    
    /**
     * 
     * @param userId
     * @return
     */
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
