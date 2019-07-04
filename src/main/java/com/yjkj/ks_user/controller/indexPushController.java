package com.yjkj.ks_user.controller;


import com.yjkj.ks_user.entity.IndexPush;
import com.yjkj.ks_user.service.IndexPushService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 16:15
 */
@Controller
@RequestMapping("indexPush")
public class indexPushController extends BaseController{

    @Autowired
    private IndexPushService indexPushService;

    @RequestMapping("getImg")
    @ResponseBody
    public YJResult getImg(String appId){
        IndexPush ip = new IndexPush();
        ip.setAppId(appId);
        List<IndexPush> list = indexPushService.findNewPush(ip);
        System.out.println(list.size());
        return YJResult.ok(list);
    }
}
