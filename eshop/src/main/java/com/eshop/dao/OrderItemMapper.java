package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.OrderItem;

public interface OrderItemMapper {
    int deleteOrderItemByPrimaryKey(Integer id);

    int insertOrderItem(OrderItem record);

    int insertOrderItemSelective(OrderItem record);

    OrderItem selectOrderItemByPrimaryKey(Integer id);

    int updateOrderItemByPrimaryKeySelective(OrderItem record);

    int updateOrderItemByPrimaryKey(OrderItem record);
    
    List<OrderItem> getOrderItemByOrderNoUserId(@Param("orderNo")Long orderNo, @Param("userId")Integer userId);

    List<OrderItem> getOrderItemByOrderNo(@Param("orderNo")Long orderNo);

    void batchInsertOrderItems(@Param("orderItemList") List<OrderItem> orderItemList);
}