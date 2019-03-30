package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.Shipping;

public interface ShippingMapper {
    int deleteShippingByPrimaryKey(Integer id);

    int insertShipping(Shipping record);

    int insertShippingSelective(Shipping record);

    Shipping selectShippingByPrimaryKey(Integer id);

    int updateShippingByPrimaryKeySelective(Shipping record);

    int updateShippingByPrimaryKey(Shipping record);
    
    int deleteShipingByShippingIdUserId(@Param("userId")Integer userId,@Param("shippingId") Integer shippingId);

    int updateShippingByShipping(Shipping record);

    Shipping selectShippingByShippingIdUserId(@Param("userId")Integer userId,@Param("shippingId") Integer shippingId);

    List<Shipping> selectShippingByUserId(@Param("userId")Integer userId);
}