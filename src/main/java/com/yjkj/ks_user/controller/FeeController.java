package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Fee")
public class FeeController extends BaseController {
	
	@Autowired
	private FeeService feeService;

}
