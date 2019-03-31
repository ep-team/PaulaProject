package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.Cart;

/**
 * 
 * @author Paula Lin
 *
 */
public interface CartMapper {
	int deleteCartByPrimaryKey(Integer id);

    int insertCart(Cart record);

    int insertCartSelective(Cart record);

    Cart selectCartByPrimaryKey(Integer id);

    int updateCartByPrimaryKeySelective(Cart record);

    int updateCartByPrimaryKey(Cart record);

    Cart selectCartByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

    List<Cart> selectOneCartByUserId(Integer userId);

    int selectDetailsCartProductCheckedStatusByUserId(Integer userId);

    int deleteByUserIdAndProductIds(@Param("userId") Integer userId,@Param("productIdList")List<String> productIdList);


    int checkedCartOrUncheckedCartProduct(@Param("userId") Integer userId,@Param("productId")Integer productId,@Param("checked") Integer checked);

    int selectCartTotalProductCount(@Param("userId") Integer userId);


    List<Cart> selectAllCheckedCartByUserId(Integer userId);
}