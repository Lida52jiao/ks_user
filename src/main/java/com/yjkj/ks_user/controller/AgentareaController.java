package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.AgentareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("Agentarea")
public class AgentareaController extends BaseController {
	
	@Autowired
	private AgentareaService agentareaService;
	
}
