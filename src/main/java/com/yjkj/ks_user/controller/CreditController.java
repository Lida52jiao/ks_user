package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Credit")
public class CreditController extends BaseController {
	
	@Autowired
	private CreditService creditService;

}
