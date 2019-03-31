package com.eshop.controller.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.common.ServerResponse;
import com.eshop.service.ICategoryService;
import com.eshop.service.IUserService;

/**
 * Description: 
 * This is the controller to handle the request for admin' access permission and user information management
 * 
 * @author Paula Lin
 *
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

	private static Logger logger = LoggerFactory.getLogger(CategoryManageController.class);
	
    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * Description: 
	 * This method handler is provided for admin user access and related validation.
	 * 
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping("add_category.do")
    @ResponseBody
    /*
     * parentId: 父节点,默认值为0,即根节点
     */
    public ServerResponse addCategory(String categoryName,@RequestParam(value = "parentId",defaultValue = "0") int parentId){
    	//全部通过拦截器验证是否登录以及是否有操作权限
    	return iCategoryService.addCategory(categoryName,parentId);
    }

    /**
     * 
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(Integer categoryId,String categoryName){
    	//全部通过拦截器验证是否登录以及是否有操作权限
    	return iCategoryService.updateCategoryName(categoryId,categoryName);
    }
    
    /**
     * 
     * @param categoryId
     * @return
     */
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
    	//全部通过拦截器验证是否登录以及是否有操作权限
    	return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    /**
     * 
     * 
     * @param categoryId
     * @return
     */
    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
    	//全部通过拦截器验证是否登录以及是否有操作权限
    	return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }
}
