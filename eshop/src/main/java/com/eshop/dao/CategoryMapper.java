package com.eshop.dao;

import java.util.List;

import com.eshop.pojo.Category;

public interface CategoryMapper {
    int deleteCategoryByPrimaryKey(Integer id);

    int insertCategory(Category record);

    int insertCategorySelective(Category record);

    Category selectCategoryByPrimaryKey(Integer id);

    int updateCategoryByPrimaryKeySelective(Category record);

    int updateCategoryByPrimaryKey(Category record);
    
    List<Category> selectCategoryChildrensByParentId(Integer parentId);
}