package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.JiDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("JiDao")
public class JiDaoController {
	
	@Autowired
	private JiDaoService jiDaoService;

}
