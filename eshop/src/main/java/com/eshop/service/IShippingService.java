package com.eshop.service;

import com.eshop.common.ServerResponse;
import com.eshop.pojo.Shipping;
import com.github.pagehelper.PageInfo;

/**
 * Created by Paula
 */
public interface IShippingService {

    ServerResponse add(Integer userId, Shipping shipping);
    ServerResponse<String> del(Integer userId,Integer shippingId);
    ServerResponse update(Integer userId, Shipping shipping);
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

}
