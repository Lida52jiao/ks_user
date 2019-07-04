package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Profit")
public class ProfitController extends BaseController {
	
	@Autowired
	private ProfitService profitService;

}
