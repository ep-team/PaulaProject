package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.Order;

/**
 * 
 * @author Paula Lin
 *
 */
public interface OrderMapper {
    int deleteOrderMapperByPrimaryKey(Integer id);

    int insertOrderMapper(Order record);

    int insertOrderMapperSelective(Order record);

    Order selectOrderMapperByPrimaryKey(Integer id);

    int updateOrderMapperByPrimaryKeySelective(Order record);

    int updateOrderMapperByPrimaryKey(Order record);
    
    Order selectOrderMapperByUserIdAndOrderNo(@Param("userId")Integer userId,@Param("orderNo")Long orderNo);

    Order selectoOrderMapperByOrderNo(Long orderNo);

    List<Order> selectOrderMapperByUserId(Integer userId);

    List<Order> selectAllOrderMapper();
}