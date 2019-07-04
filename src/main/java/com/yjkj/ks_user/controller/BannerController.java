package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Banner;
import com.yjkj.ks_user.service.BannerService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("Banner")
public class BannerController extends BaseController {
	
	@Autowired
	private BannerService bannerService;
	
	//获取轮播图
	@RequestMapping(value = "selectBannerList")
	public @ResponseBody
	YJResult get(String appId, String status) {
		Banner banner=new Banner();
		banner.setAppId(appId);
		banner.setStatus(status);
		List<Banner> bannerList=bannerService.queryObjectForList(banner);
		return YJResult.ok(bannerList);
	}
}
