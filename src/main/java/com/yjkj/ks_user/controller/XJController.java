package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.XJ;
import com.yjkj.ks_user.service.XJService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("XJ")
public class XJController extends BaseController {
	
	@Autowired
	private XJService xjService;
	
	@RequestMapping(value = "get")
	public @ResponseBody
	YJResult get() {
			XJ t = new XJ();
			t.setId((long)1);
			XJ xj = xjService.findByObject(t);
			return YJResult.ok(xj);
		}

}
