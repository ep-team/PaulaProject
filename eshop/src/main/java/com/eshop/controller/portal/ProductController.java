package com.eshop.controller.portal;

import com.eshop.common.ServerResponse;
import com.eshop.service.IProductService;
import com.eshop.vo.ProductDetailVo;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product/")
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private IProductService iProductService;

    /*
     * 前台查看商品详情
     */
  //二期-start
    /*@RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }
    */
    //改造成restful
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId){
        return iProductService.getProductDetail(productId);
    }
  //二期-end

    
    
    /*
     * 前台用户搜索请求
     * 由于返回值是分页列表,故为ServerResponse<PageInfo>
     * @RequestParam中的required默认为true, 即为mandatory
     */
  //二期-start
  /*  @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }
*/
    //改造成restful, 但对于必须填, 只能逻辑上来添加
    //http://www.happymmall.com/product/手机/100012/1/10/price_asc
    @RequestMapping(value = "/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@PathVariable(value = "keyword")String keyword,
                                         @PathVariable(value = "categoryId")Integer categoryId,
                                         @PathVariable(value = "pageNum") Integer pageNum,
                                         @PathVariable(value = "pageSize") Integer pageSize,
                                         @PathVariable(value = "orderBy") String orderBy){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        if(StringUtils.isBlank(orderBy)){
            orderBy = "price_asc";
        }

        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }

/*
    //用于兼容keyword不传的情况
   //  http://www.happymmall.com/product/100012/1/10/price_asc, 不能区分请求的是"/{categoryId}/{pageNum}/{pageSize}/{orderBy}"
    //还是 "/{keyword}/{pageNum}/{pageSize}/{orderBy}"
  @RequestMapping(value = "/{categoryId}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<PageInfo> listBadcase(@PathVariable(value = "categoryId")Integer categoryId,
                                              @PathVariable(value = "pageNum") Integer pageNum,
                                              @PathVariable(value = "pageSize") Integer pageSize,
                                              @PathVariable(value = "orderBy") String orderBy){
      if(pageNum == null){
          pageNum = 1;
      }
      if(pageSize == null){
          pageSize = 10;
      }
      if(StringUtils.isBlank(orderBy)){
          orderBy = "price_asc";
      }

      return iProductService.getProductByKeywordCategory("",categoryId,pageNum,pageSize,orderBy);
  }

   //用于兼容categoryId不传的情况
  @RequestMapping(value = "/{keyword}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<PageInfo> listBadcase(@PathVariable(value = "keyword")String keyword,
                                              @PathVariable(value = "pageNum") Integer pageNum,
                                              @PathVariable(value = "pageSize") Integer pageSize,
                                              @PathVariable(value = "orderBy") String orderBy){
      if(pageNum == null){
          pageNum = 1;
      }
      if(pageSize == null){
          pageSize = 10;
      }
      if(StringUtils.isBlank(orderBy)){
          orderBy = "price_asc";
      }

      return iProductService.getProductByKeywordCategory(keyword,null,pageNum,pageSize,orderBy);
  }
*/
    
//用于兼容keyword不传的情况
    //自定义资源占位keyword, 来将模棱两可的请求进行区分, keyword代表第一个资源占位是以keyword开始的
  //http://www.happymmall.com/product/keyword/手机/1/10/price_asc
  @RequestMapping(value = "/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<PageInfo> list(@PathVariable(value = "keyword")String keyword,
                                                     @PathVariable(value = "pageNum") Integer pageNum,
                                                     @PathVariable(value = "pageSize") Integer pageSize,
                                                     @PathVariable(value = "orderBy") String orderBy){
      if(pageNum == null){
          pageNum = 1;
      }
      if(pageSize == null){
          pageSize = 10;
      }
      if(StringUtils.isBlank(orderBy)){
          orderBy = "price_asc";
      }

      return iProductService.getProductByKeywordCategory(keyword,null,pageNum,pageSize,orderBy);
  }

//用于兼容categoryId不传的情况
//自定义资源占位category, 来将模棱两可的请求进行区分, category代表第一个资源占位是以category开始的
  //http://www.happymmall.com/product/category/100012/1/10/price_asc
  @RequestMapping(value = "/category/{categoryId}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
  @ResponseBody
  public ServerResponse<PageInfo> list(@PathVariable(value = "categoryId")Integer categoryId,
                                                     @PathVariable(value = "pageNum") Integer pageNum,
                                                     @PathVariable(value = "pageSize") Integer pageSize,
                                                     @PathVariable(value = "orderBy") String orderBy){
      if(pageNum == null){
          pageNum = 1;
      }
      if(pageSize == null){
          pageSize = 10;
      }
      if(StringUtils.isBlank(orderBy)){
          orderBy = "price_asc";
      }

      return iProductService.getProductByKeywordCategory("",categoryId,pageNum,pageSize,orderBy);
  }
  //二期-end

}
