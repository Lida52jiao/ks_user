package com.yjkj.ks_user.controller;

import com.alibaba.fastjson.JSONObject;
import com.yjkj.ks_user.entity.Agent;
import com.yjkj.ks_user.entity.AgentAllMessage;
import com.yjkj.ks_user.entity.MerChant;
import com.yjkj.ks_user.entity.Transaction;
import com.yjkj.ks_user.service.AgentService;
import com.yjkj.ks_user.service.MerChantsService;
import com.yjkj.ks_user.service.TransactionService;
import com.yjkj.ks_user.util.YJResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询该商户的直接代理
 * @author N
 *
 */
@Controller
@RequestMapping("/KSOneAgentMessage/")
public class KSMerchantsAgentCtl {
	private static final Log logger = LogFactory.getLog(KSMerchantsAgentCtl.class);
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("oneAgentMessage")
	@ResponseBody
	public YJResult getmerchantsOneAgent(String merChantId){
		//查询商户的直接代理
		MerChant mer = new MerChant();
		mer.setMerChantId(merChantId);
		MerChant newMer = merChantsService.gain(mer);
		logger.info("商户信息为："+JSONObject.toJSONString(newMer));
		AgentAllMessage agentAllMessage = new AgentAllMessage();
		//该商户不为空
		if(newMer != null){
			//查询代理信息
			Agent agent = new Agent();
			agent.setMerId(newMer.getAgentId());
			Agent newAgent = agentService.gain(agent);
			logger.info("第一次查询归属代理信息为："+JSONObject.toJSONString(agentAllMessage));
			agentAllMessage.setAgentId(newAgent.getMerId());
			agentAllMessage.setAgentName(newAgent.getMerName());
			agentAllMessage.setMerMp(newAgent.getMerMp());
			Transaction entity = new Transaction();
			entity.setMerId(newAgent.getMerId());
			Transaction tra = transactionService.findByObject(entity);
			agentAllMessage.setMerChantId(tra.getMerChantId());
			agentAllMessage.setAgentLevel(tra.getAgentStatus());
			//该商户直接代理不为空
			return new YJResult("0000", "查询成功", agentAllMessage);
		}
		return new YJResult("0002", "查无此商户", newMer);
	}

}
