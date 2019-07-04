package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Products;
import com.yjkj.ks_user.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/25 16:58
 */
@Controller
@RequestMapping(value ="Products")
public class ProductsController extends BaseController {
    @Autowired
    private ProductsService productsService;
    @RequestMapping("select")
    @ResponseBody
    public Products getpro(Products p){
        Products np = new Products();
        np.setAppId(p.getAppId());
        np.setType(p.getType());
        return productsService.findByObject(np);
    }

}
