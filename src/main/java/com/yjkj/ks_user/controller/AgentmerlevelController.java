package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.service.AgentmerlevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Agentmerlevel")
public class AgentmerlevelController extends BaseController {
	
	@Autowired
	private AgentmerlevelService agentmerlevelService;

}
