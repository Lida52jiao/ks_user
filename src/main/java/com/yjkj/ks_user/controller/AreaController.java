package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("Area")
public class AreaController extends BaseController {
	
	@Autowired
	private AreaService areaService;

}
