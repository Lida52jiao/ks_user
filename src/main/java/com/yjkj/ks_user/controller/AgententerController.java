package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.AgentEnter;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.service.AgententerService;
import com.yjkj.ks_user.service.MerChantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("enter")
public class AgententerController extends BaseController {
	@Autowired
	private AgententerService agententerService;
	@Autowired
	private MerChantsService merChantsService;
	
	@RequestMapping("addAgent")
	public String addDept(String entName, String entMp, String entMail, String entArea, String merChantId)
	{
		//调用service进行添加
		AgentEnter agent = new AgentEnter();
		agent.setEntMail(entMail);
		agent.setEntMp(entMp);
		agent.setEntName(entName);
		agent.setEntArea(entArea);
		agent.setMerChantId(merChantId);
		MerChants mer=new MerChants();
		mer.setMerChantId(merChantId);
		mer=merChantsService.findByObject(mer);
		agent.setMerName(mer.getMerName());
		agent.setMerMp(mer.getMerMp());
		agent.setAgentId(mer.getAgentId());
		agent.setInstitutionId(mer.getInstitutionId());
		agent.setAppId(mer.getAppId());
		agententerService.save(agent);
		return "success";
	}
	

		
}
