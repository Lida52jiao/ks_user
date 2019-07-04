package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Income;
import com.yjkj.ks_user.service.IncomeService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Income")
public class IncomeController extends BaseController {
	
	@Autowired
	private IncomeService incomeService; 
	
	@RequestMapping(value = "selectIncome")
	public @ResponseBody
	YJResult get(String merType) {
		Income t = new Income();
		t.setMerType(merType);
		Income n = incomeService.findByObject(t);
		return YJResult.ok(n);
	}

}
