package com.eshop.dao.test;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eshop.dao.CategoryMapper;
import com.eshop.pojo.Category;
import com.eshop.service.impl.CategoryServiceImpl;
import com.eshop.test.TestBase;

/**
 * Created by Paula
 */
public class CategoryDaoTest extends TestBase {


    private CategoryMapper categoryMapper;
    private CategoryServiceImpl iCategoryService;

    public void getCategoryChild(){
        Category d  = categoryMapper.selectCategoryByPrimaryKey(1);
        System.out.println(d);
        Category d4  = categoryMapper.selectCategoryByPrimaryKey(4);
        System.out.println(d4);
    }
    public void testChildService(){
        iCategoryService.selectCategoryAndChildrenById(2);
    }

}
