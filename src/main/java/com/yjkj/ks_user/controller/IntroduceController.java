package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.IntroduceService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("Introduce")
public class IntroduceController extends BaseController {
	
	@Autowired
	private IntroduceService introduceService;
	
	@RequestMapping(value = "selectIntroduceList")
	public @ResponseBody
	YJResult get(Integer rows, Integer page) {
		Map<String,Object> introduces=introduceService.receive(page,rows);
		return YJResult.ok(introduces);
	}
	
}
