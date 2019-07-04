package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("Type")
public class TypeController extends BaseController {
	
	@Autowired
	private TypeService typeService;

}
