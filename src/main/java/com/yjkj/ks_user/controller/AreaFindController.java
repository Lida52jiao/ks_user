package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Area;
import com.yjkj.ks_user.service.AreaService;
import com.yjkj.ks_user.util.YJResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Create By DaDa
 * @Date: 2019/4/22 16:59
 */
@Controller
@RequestMapping("FindAreaById")
public class AreaFindController extends BaseController {
    @Autowired
    private AreaService areaService;

    @RequestMapping("findArea")
    @ResponseBody
    public YJResult findArea(String id){
        Area a = new Area();
        if(StringUtils.isBlank(id)){
            a.setParent_id("0");
        }else {
            a.setParent_id(id);
        }
        List<Area> list = areaService.queryObjectForList(a);
        System.out.println(list.size());
        return YJResult.ok(list);
    }
}
