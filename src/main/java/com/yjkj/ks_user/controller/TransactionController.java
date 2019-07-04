package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.Agent;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.entity.Transaction;
import com.yjkj.ks_user.service.AgentService;
import com.yjkj.ks_user.service.MerChantsService;
import com.yjkj.ks_user.service.TransactionService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("Transaction")
public class TransactionController extends BaseController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;

	//查商户是否是代理商
	@RequestMapping(value = "whetherAgent")
	public @ResponseBody
	YJResult whetherAgent(String merChantId) {
		Transaction n = new Transaction();
		n.setMerChantId(merChantId);
		Transaction h=transactionService.findByObject(n);
		if(h != null){
				return YJResult.ok(h.getMerId());
			}
		return YJResult.build(Constaint.NONE_AGENT, "商户不是代理商");
	}
	
	//获取代理商列表
	@RequestMapping(value = "getTransactionList")
	public @ResponseBody
    List<Transaction> select(String merChantId) {
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
			for(Agent n:agentList){
				Agent s=(Agent)n;
				Transaction transaction=new Transaction();
				transaction.setMerId(s.getMerId());
				Transaction transactions=transactionService.findByObject(transaction);
				if(transactions != null){
					tList.add(transactions);
				}
			}
			return tList;
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
	
	//获取平台的信息
	@RequestMapping(value = "selectInstitutionId")
	public @ResponseBody
	Transaction get() {
		Transaction n = new Transaction();
		n.setMerId(Constaint.AGENT);
		return transactionService.findByObject(n);
	}

}
