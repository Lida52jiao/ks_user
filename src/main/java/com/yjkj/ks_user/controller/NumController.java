package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.*;
import com.yjkj.ks_user.entity.Number;
import com.yjkj.ks_user.service.*;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("Num")
public class NumController extends BaseController {
	
	@Autowired
	private NumService numService; 
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentareaService agentareaService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private TypeService typeService;
	
	//获取返佣
	@RequestMapping(value = "selectNum")
	public @ResponseBody
	YJResult get(String vipType) {
		if("T".equals(vipType)){
			Num n = new Num();
			n.setVipType(vipType);
			return YJResult.ok(numService.queryObjectForList(n));
		}
		Num n = new Num();
		n.setVipType(vipType);
		List<Number> t = numService.gain(n);
		return YJResult.ok(t);
	}
	
	//支付宝分润
	@RequestMapping(value = "getNum")
	public @ResponseBody
	Maid select(String merChantId, String payType) {
		Num num = new Num();
		num.setPayType(payType);
		Num n = numService.findByObject(num);
		MerChants merChant = new MerChants();
		merChant.setMerChantId(merChantId);
		MerChants merChants = merChantsService.findByObject(merChant);
		
		//归属代理商
		Agent s = new Agent();
		s.setMerId(merChants.getAgentId());
		Agent t = agentService.findByObject(s);
		Transaction tran = new Transaction(); 
		tran.setMerId(merChants.getAgentId());
		Transaction k = transactionService.findByObject(tran);
		
		List<Area> tList = getArea(merChants);
		
		if(merChants.getInstitutionId().equals(merChants.getAgentId())){
			Maid maid = new Maid();
			maid.setMerType(n.getMerType());
			maid.setNum(n.getNum());
			maid.setValidTime(n.getValidTime());
			maid.setOneMerchant(n.getOneMerchant());
			maid.setTwoMerchant(n.getTwoMerchant());
			maid.setThreeMerchant(n.getThreeMerchant());
			maid.setOneAgent(n.getOneAgent());
			maid.setTwoAgent(n.getTwoAgent());
			maid.setCountyAgent(n.getCountyAgent());
			maid.setCityAgent(n.getCityAgent());
			maid.setProvinceAgent(n.getProvinceAgent());
			maid.setInstitution(n.getInstitution());
			maid.setAmount(n.getAmount());
			maid.setLevel(n.getLevel());
			maid.setPayType(n.getPayType());
			maid.setVipType(n.getVipType());
			maid.setTopAgent(n.getTopAgent());
			maid.setOneMerchantId(merChants.getOneMerId());
			maid.setTwoMerchantId(merChants.getTwoMerId());
			maid.setThreeMerchantId(merChants.getThreeMerId());
			maid.setOneAgentId(k.getMerChantId());
			if(!tList.isEmpty()){
				Agentarea countryAgent = getAgentarea(tList.get(0).getCode());
				Agentarea cityAgent = getAgentarea(tList.get(1).getCode());
				Agentarea provinceAgent = getAgentarea(tList.get(2).getCode());
				if(countryAgent != null){
					maid.setCountyAgentId(countryAgent.getMerchantId());
				}
				if(cityAgent != null){
					maid.setCityAgentId(cityAgent.getMerchantId());
				}
				if(provinceAgent != null){
					maid.setProvinceAgentId(provinceAgent.getMerchantId());
				}
			}
			maid.setMerInstitutionId(k.getMerChantId());
			return maid;
		}
		
		//上上代理商
		Transaction transaction = new Transaction(); 
		transaction.setMerId(t.getOneMerId());
		Transaction h = transactionService.findByObject(transaction);
		
		List<Agent> list = selects(merChantId);
		//顶级代理商
		List<Agent> lists = new ArrayList<Agent>();
		for(Agent agents : list){
			Agent agent = (Agent)agents;
			if(merChants.getInstitutionId().equals(agent.getOneMerId())){
				lists.add(agent);
			}
			if(merChants.getInstitutionId().equals(agent.getMerId())){
				lists.add(agent);
			}
		}
		Transaction trans = new Transaction();
		trans.setMerId(lists.get(0).getMerId());
		Transaction b = transactionService.findByObject(trans);
		Transaction transactions = new Transaction();
		transactions.setMerId(lists.get(1).getMerId());
		Transaction ttt = transactionService.findByObject(transactions);
		
		
		
		Type type = new Type();
		type.setId((long)1);
		Type types = typeService.findByObject(type);
		if("N".equals(types.getMinOrNot())){
			Maid maid = new Maid();
			maid.setMerType(n.getMerType());
			maid.setNum(n.getNum());
			maid.setValidTime(n.getValidTime());
			MerChants oneMerchant = new MerChants();
			oneMerchant.setMerChantId(merChants.getOneMerId());
			MerChants oneMerchants = merChantsService.findByObject(oneMerchant);
			if(oneMerchants != null){
				if("1".equals(oneMerchants.getMerType())){
					maid.setOneMerchant("0");
				}else{
					maid.setOneMerchant(n.getOneMerchant());
				}
			}
			if(merChants.getTwoMerId() != null){
				MerChants twoMerchant = new MerChants();
				twoMerchant.setMerChantId(merChants.getTwoMerId());
				MerChants twoMerchants = merChantsService.findByObject(twoMerchant);
				if(twoMerchants != null){
					if("1".equals(twoMerchants.getMerType())){
						maid.setTwoMerchant("0");
					}else{
						maid.setTwoMerchant(n.getTwoMerchant());
					}
				}
			}else{
				maid.setTwoMerchant("0");
			}
			if(merChants.getThreeMerId() != null){
				MerChants threeMerchant = new MerChants();
				threeMerchant.setMerChantId(merChants.getThreeMerId());
				MerChants threeMerchants = merChantsService.findByObject(threeMerchant);
				if(threeMerchants != null){
					if("1".equals(threeMerchants.getMerType())){
						maid.setThreeMerchant("0");
					}else{
						maid.setThreeMerchant(n.getThreeMerchant());
					}
				}
			}else{
				maid.setThreeMerchant("0");
			}
			maid.setOneAgent(n.getOneAgent());
			maid.setTwoAgent(n.getTwoAgent());
			maid.setCountyAgent(n.getCountyAgent());
			maid.setCityAgent(n.getCityAgent());
			maid.setProvinceAgent(n.getProvinceAgent());
			maid.setInstitution(n.getInstitution());
			maid.setAmount(n.getAmount());
			maid.setLevel(n.getLevel());
			maid.setPayType(n.getPayType());
			maid.setVipType(n.getVipType());
			maid.setTopAgent(n.getTopAgent());
			maid.setOneMerchantId(merChants.getOneMerId());
			maid.setTwoMerchantId(merChants.getTwoMerId());
			maid.setThreeMerchantId(merChants.getThreeMerId());
			maid.setOneAgentId(k.getMerChantId());
			maid.setTwoAgentId(h.getMerChantId());
			if(!tList.isEmpty()){
				Agentarea countryAgent = getAgentarea(tList.get(0).getCode());
				Agentarea cityAgent = getAgentarea(tList.get(1).getCode());
				Agentarea provinceAgent = getAgentarea(tList.get(2).getCode());
				if(countryAgent != null){
					maid.setCountyAgentId(countryAgent.getMerchantId());
				}
				if(cityAgent != null){
					maid.setCityAgentId(cityAgent.getMerchantId());
				}
				if(provinceAgent != null){
					maid.setProvinceAgentId(provinceAgent.getMerchantId());
				}
			}
			maid.setMerInstitutionId(ttt.getMerChantId());
			maid.setTopAgentId(b.getMerChantId());
			return maid;
		}else{
			Maid maid = new Maid();
			maid.setMerType(n.getMerType());
			maid.setNum(n.getNum());
			maid.setValidTime(n.getValidTime());
			maid.setOneMerchant(n.getOneMerchant());
			maid.setTwoMerchant(n.getTwoMerchant());
			maid.setThreeMerchant(n.getThreeMerchant());
			maid.setOneAgent(n.getOneAgent());
			maid.setTwoAgent(n.getTwoAgent());
			maid.setCountyAgent(n.getCountyAgent());
			maid.setCityAgent(n.getCityAgent());
			maid.setProvinceAgent(n.getProvinceAgent());
			maid.setInstitution(n.getInstitution());
			maid.setAmount(n.getAmount());
			maid.setLevel(n.getLevel());
			maid.setPayType(n.getPayType());
			maid.setVipType(n.getVipType());
			maid.setTopAgent(n.getTopAgent());
			maid.setOneMerchantId(merChants.getOneMerId());
			maid.setTwoMerchantId(merChants.getTwoMerId());
			maid.setThreeMerchantId(merChants.getThreeMerId());
			maid.setOneAgentId(k.getMerChantId());
			maid.setTwoAgentId(h.getMerChantId());
			if(!tList.isEmpty()){
				Agentarea countryAgent = getAgentarea(tList.get(0).getCode());
				Agentarea cityAgent = getAgentarea(tList.get(1).getCode());
				Agentarea provinceAgent = getAgentarea(tList.get(2).getCode());
				if(countryAgent != null){
					maid.setCountyAgentId(countryAgent.getMerchantId());
				}
				if(cityAgent != null){
					maid.setCityAgentId(cityAgent.getMerchantId());
				}
				if(provinceAgent != null){
					maid.setProvinceAgentId(provinceAgent.getMerchantId());
				}
			}
			maid.setMerInstitutionId(ttt.getMerChantId());
			maid.setTopAgentId(b.getMerChantId());
			return maid;
		}
	}
	
	//查询上级代理商
	List<Agent> selects(String merChantId) {
		MerChants m = new MerChants();
		m.setMerChantId(merChantId);
		MerChants h = merChantsService.findByObject(m);
		Agent agent=new Agent();
		agent.setMerId(h.getAgentId());
		Agent t=agentService.findByObject(agent);
		List<Agent> list=new ArrayList<Agent>();
		List<Agent> agentList=new ArrayList<Agent>();
		agentList.add(t);
		agentList.addAll(get(list,t));
		return agentList;
	}
	
	//递归
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
		
	//获取省市县
	List<Area> getArea(MerChants merChants) {
		String s = merChants.getCertNo().substring(0, 6);
		Area area = new Area();
		area.setCode(s);
		Area t = areaService.findByObject(area);
		List<Area> list = new ArrayList<Area>();
		if(t != null){
			list.add(t);
			Area k = new Area();
			k.setId(Long.parseLong(t.getParent_id()));
			Area n = areaService.findByObject(k);
			list.add(n);
			Area b = new Area(); 
			b.setId(Long.parseLong(n.getParent_id()));
			Area tt = areaService.findByObject(b);
			list.add(tt);
		}
		return list;
	}

	//获取省市县的代理商
	Agentarea getAgentarea(String code) {
		Agentarea agentarea = new Agentarea();
		agentarea.setAreaCode(code);
		Agentarea h = agentareaService.findByObject(agentarea);
		return h;
	}

}
