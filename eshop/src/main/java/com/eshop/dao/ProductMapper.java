package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.Product;

public interface ProductMapper {
    int deleteProductMapperByPrimaryKey(Integer id);

    int insertProductMapper(Product record);

    int insertProductMapperSelective(Product record);

    Product selectProductMapperByPrimaryKey(Integer id);

    int updateProductMapperByPrimaryKeySelective(Product record);

    int updateProdctMapperByPrimaryKey(Product record);
    
    List<Product> selectProductMapperList();

    List<Product> selectProductByNameAndProductId(@Param("productName")String productName,@Param("productId") Integer productId);

    List<Product> selectProductByNameAndCategoryIds(@Param("productName")String productName,@Param("categoryIdList")List<Integer> categoryIdList);

}