package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Agent;
import com.yjkj.ks_user.entity.AgentAllMessage;
import com.yjkj.ks_user.entity.MerChants;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 查询该商户的直接运营商
 * @author N
 *
 */
@Controller
@RequestMapping("/KSOneBusinessMessage/")
public class KSMerchantsBusinessCtl {
	private static final Log logger = LogFactory.getLog(KSMerchantsBusinessCtl.class);
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("oneBusinessMessage")
	@ResponseBody
	public YJResult getmerchantsOneAgent(String merChantId){
		MerChants m = new MerChants();
		m.setMerChantId(merChantId);
		MerChants h = merChantsService.findByObject(m);
		
		Agent agent=new Agent();
		agent.setMerId(h.getAgentId());
		Agent t=agentService.findByObject(agent);
		List<Agent> list=new ArrayList<Agent>();
		List<Transaction> tList=new ArrayList<Transaction>();
		List<Agent> agentList=new ArrayList<Agent>();
		agentList.add(t);
		agentList.addAll(get(list,t));
		AgentAllMessage agentAllMessage = new AgentAllMessage();
		for(Agent n:agentList){
			Agent s=(Agent)n;
			Transaction transaction=new Transaction();
			transaction.setMerId(s.getMerId());
			Transaction transactions=transactionService.findByObject(transaction);
			if(transactions != null){
				if(transactions.getAgentStatus().equals("2")){
					agentAllMessage.setAgentId(s.getMerId());
					agentAllMessage.setAgentName(s.getMerName());
					agentAllMessage.setMerMp(s.getMerMp());
					agentAllMessage.setMerChantId(transactions.getMerChantId());
					agentAllMessage.setAgentLevel(transactions.getAgentStatus());
					return new YJResult("0000", "查询成功", agentAllMessage);
				}
			}
		}
		
		return new YJResult("0001", "查询失败", agentAllMessage);
		
		
	}

	public List<Agent> get(List<Agent> list, Agent t){
		if("".equals(t.getOneMerId()) || null == t.getOneMerId()){
			return list;
		}
		if(!"".equals(t.getOneMerId()) && null != t.getOneMerId()){
			Agent agent=new Agent();
			agent.setMerId(t.getOneMerId());
			Agent n=agentService.findByObject(agent);
			list.add(n);
			get(list,n);
		}
		return list;	
	}
	
	
	@RequestMapping("one")
	@ResponseBody
	public YJResult getmerchants(String merChantId){
		MerChants m = new MerChants();
		m.setMerChantId(merChantId);
		MerChants h = merChantsService.findByObject(m);
		
		Agent agent=new Agent();
		agent.setMerId(h.getAgentId());
		Agent t=agentService.findByObject(agent);
		List<Agent> list=new ArrayList<Agent>();
		List<Agent> agentList=new ArrayList<Agent>();
		agentList.add(t);
		agentList.addAll(getone(list,t));
		return new YJResult("0001", "查询失败", agentList);
		
		
	}
	
	public List<Agent> getone(List<Agent> list, Agent t){
		Agent agent=new Agent();
		agent.setOneMerId(t.getMerId());
		Agent n=agentService.findByObject(agent);
		list.add(n);
		if(n!=null){
		getone(list,n);
		}
		return list;
	}
}
