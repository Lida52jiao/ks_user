package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Used;
import com.yjkj.ks_user.service.UsedService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("Used")
public class UsedController extends BaseController {
	
	@Autowired
	private UsedService usedService;
	
	@RequestMapping(value = "getUsed")
	public @ResponseBody
	YJResult get() {
		List<Used> n=usedService.queryObjectForList();
		return YJResult.ok(n);	
	}

}
