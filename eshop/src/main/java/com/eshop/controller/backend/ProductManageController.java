package com.eshop.controller.backend;

import com.eshop.common.ServerResponse;
import com.eshop.pojo.Product;
import com.eshop.service.IFileService;
import com.eshop.service.IProductService;
import com.eshop.service.IUserService;
import com.eshop.utilities.PropertiesUtil;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by paula
 */

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

	private static Logger logger = LoggerFactory.getLogger(ProductManageController.class);
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IFileService iFileService;

    /*
     * 商品新增或更新
     */
    @RequestMapping("save.do")
    @ResponseBody
   public ServerResponse productSave(Product product){
    	//填充我们增加产品的业务逻辑
        return iProductService.saveOrUpdateProduct(product);
    }
  
    /*
     * 通过更新状态来实现商品的上下架更新
     */
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(Integer productId,Integer status){
    	return iProductService.setSaleStatus(productId,status);
    }

    /*
     * 获取商品详情功能
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(Integer productId){
    	return iProductService.manageProductDetail(productId);
    }
 
    /*
     * 获取产品列表->列表要做动态分页(内嵌com.github.pagehelper.pagehelper来做动态分页)
     * pageNum是第几页,默认为1
     * pageSize是页面容量,默认为10
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
    	 return iProductService.getProductList(pageNum,pageSize);
    }
    
    /*
     * 根据productId或productName进行产品搜索
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSearch(String productName,Integer productId, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
    	return iProductService.searchProduct(productName,productId,pageNum,pageSize);
    }

    /*
     * SpringMVC 文件上传
     * @param MultipartFile file ->SpringMVC的文件上传
     * @param HttpServletRequest request ->可以根据上下文动态地创建相对路径出来
     * @RequestParam->可以指定前端入参的参数名,如在index.jsp中, ,required = false表示入参不是必须的
     */
    @RequestMapping("upload.do")
    @ResponseBody
  public ServerResponse upload(HttpServletRequest request, @RequestParam(value = "upload_file",required = false) MultipartFile file){
    	String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file,path);
        String url = PropertiesUtil.getStringProperty("ftp.server.http.prefix")+targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }

    /*
     * 富文本文件上传
     */
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "upload_file",required = false) MultipartFile file){
    	Map resultMap = Maps.newHashMap();
    	String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file,path);
        if(StringUtils.isBlank(targetFileName)){
            resultMap.put("success",false);
            resultMap.put("msg","上传失败");
            return resultMap;
        }
        String url = PropertiesUtil.getStringProperty("ftp.server.http.prefix")+targetFileName;
        resultMap.put("success",true);
        resultMap.put("msg","上传成功");
        resultMap.put("file_path",url);
        
        //富文本上传需要修改HttpServletResponse的header
        response.addHeader("Access-Control-Allow-Headers","X-File-Name");
        return resultMap;
    }
    
}
