package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.KSMerUsedArea;
import com.yjkj.ks_user.service.MerchantsUsedAreaService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 卡神实名环节
 * 存储常用地区
 * 及其他信息的表
 * @author N
 *
 */
@Controller
@RequestMapping("/KSUsedArea/")
public class KSRealNameUsedAreaCtl extends BaseController {

	@Autowired
	private MerchantsUsedAreaService merchantsUsedAreaService;
	
	@RequestMapping("areaMerMessage")
	@ResponseBody
	public YJResult insertRecord(KSMerUsedArea ksMerUsedArea){

		ksMerUsedArea.setCreatedTime(new Date());
		String count = merchantsUsedAreaService.save(ksMerUsedArea);
		if("success".equals(count)){
			return new YJResult("0000", "常用地区保存成功", "");
		}
		return new YJResult("0001", "常用地区保存失败", "");
	}
}
