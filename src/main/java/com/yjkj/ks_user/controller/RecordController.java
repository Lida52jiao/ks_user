package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Record")
public class RecordController extends BaseController {
	
	@Autowired
	private RecordService recordService; 

}
