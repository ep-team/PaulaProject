package com.eshop.dao;

import com.eshop.pojo.PayInfo;

public interface PayInfoMapper {
    int deletePayInfoByPrimaryKey(Integer id);

    int insertPayInfo(PayInfo record);

    int insertPayInfoSelective(PayInfo record);

    PayInfo selectPayInfoByPrimaryKey(Integer id);

    int updatePayInfoByPrimaryKeySelective(PayInfo record);

    int updatePayInfoByPrimaryKey(PayInfo record);
}