package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Agent")
public class AgentController extends BaseController {

	@Autowired
	private AgentService agentService;

}
