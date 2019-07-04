package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.*;
import com.yjkj.ks_user.mapper.MerChantsMapper;
import com.yjkj.ks_user.service.*;
import com.yjkj.ks_user.util.PasswordHelper;
import com.yjkj.ks_user.util.YJ;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OpenAccountServiceImpl implements OpenAccountService{

	private static final Log logger = LogFactory.getLog(OpenAccountServiceImpl.class);
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private MerChantsMapper merChantsMapper;
	@Autowired
	private AgentService agentService;
	@Autowired
	private UserService userService;
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private KSAgentRateTableSAervice ksAgentRateTableSAervice;
	@Autowired
	private AgentRateService agentRateService;
	@Autowired
	private RatesService ratesService;
	@Autowired
	private AgentLevelMerTypeService agentLevelMerTypeService;
	@Autowired
	private MerChantsRateService merchantsRateService;
	
	public void openAccount(MerChants newMer, String merChantId, String oneMerId) {
		
		//省级代理商、进行开户、绑定关系操作
		UserEntity userFormMap = new UserEntity();
		userFormMap.setAccountName(newMer.getMerMp());
		userFormMap.setPassWord("12345");
		UserEntity r=PasswordHelper.encryptPassword(userFormMap);
		Agent n=new Agent(newMer.getMerName(), newMer.getMerMp(), newMer.getMerMp(), r.getPassWord(), ".com", newMer.getAgentId(), System.currentTimeMillis()+"");
		agentService.save(n);
		Agent h=agentService.findByObject(n);
		String sagentId = "C" + YJ.formatDate(new Date()) + YJ.formattime(new Date());
		long id = h.getId() + 10000;
		String merId = sagentId + id;
		h.setMerId(merId);
		h.setTotalCode("0");
		h.setAssign("0");
		h.setGeneratedCode("0");
		h.setUsed("0");
		h.setNotused("0");
		agentService.update(h);
		UserEntity k=new UserEntity();
		k.setAccountName(newMer.getMerMp());
		k.setDate(YJ.formatDate(new Date())+YJ.formattime(new Date()));
		k.setEmail(".com");
		k.setPassWord("12345");
		k.setMerId(merId);
		k.setTel(newMer.getMerMp());
		k.setUserName(newMer.getMerName());
		k.setLocked("1");
		PasswordHelper.encryptPassword(k);
		userService.save(k);
		//代理商绑定关系
		Transaction savetransation=new Transaction();
		savetransation.setMerId(merId);
		savetransation.setAgentName(newMer.getMerName());
		savetransation.setMerChantId(merChantId);
		savetransation.setCreatDate(String.valueOf(new Date()));
		savetransation.setAgentStatus("1");
		String message = transactionService.save(savetransation);
		//代理商绑定通道费率
		openFee(merId,merChantId);
		//商户成代理带走自己的直推、间推、间间推
		daizouMer(merChantId,merId,newMer);
		//修改商户的归属代理商号为自己
		MerChants mert = new MerChants();
		mert.setMerChantId(merChantId);
		MerChants mer = merChantsService.findByObject(mert);
		mer.setAgentId(merId);
		merChantsService.update(mer);
		//bnindAgentMessageUtil.openAgent(v.getMerMp());//绑定代理成功发送短信
		//同步带走代理之间的归属（带走代理，带走不算自己的下面四级代理）
		//自己是谁的直推、间推、间间推及间间推的直推，就是自己要带走的代理
		MerChants onea = new MerChants();
		onea.setOneMerId(merChantId);
		List<MerChants> onealist = merChantsService.queryObjectForList(onea);//直推列表
		MerChants twoa = new MerChants();
		twoa.setTwoMerId(merChantId);
		List<MerChants> twoalist = merChantsService.queryObjectForList(twoa);//间推列表
		MerChants threea = new MerChants();
		threea.setThreeMerId(merChantId);
		List<MerChants> threealist = merChantsService.queryObjectForList(threea);//间间推列表
		List<MerChants> allList = new ArrayList<MerChants>();//存储所有的商户
		List<MerChants> notAgentList = new ArrayList<MerChants>();//存储间间间推不是代理的人
		allList.addAll(onealist);
		allList.addAll(twoalist);
		allList.addAll(threealist);
		if(threealist.size()!=0){
			//遍历间间推列表拿到他们的商户号，查看
			//是谁的直推，为第四级别
			for(MerChants four:threealist){
				MerChants foura = new MerChants();
				foura.setOneMerId(four.getMerChantId());
				List<MerChants> fouralist = merChantsService.queryObjectForList(foura);//间间推作为直推的列表
				//查看他的间间间推里面有没有代理，是代理的话就不算做四级之内
				for(MerChants agentOrNot:fouralist){
					//不是代理才添加进去
					if(agentOrNot.getAgentStatus().equals("N")){
						notAgentList.add(agentOrNot);
					}
				}
				allList.addAll(notAgentList);
			}
		}
		if(allList.size()!=0){
			for(MerChants last:allList){
				if(!last.getAgentStatus().equals("N")){//只管代理
					Transaction trana=new Transaction();//去中间表查该商户的代理商号
					trana.setMerChantId(last.getMerChantId());
					Transaction trananeed=transactionService.findByObject(trana);
					Agent agenta = new Agent();
					agenta.setMerId(trananeed.getMerId());
					Agent agentneed = agentService.findByObject(agenta);//查询出该代理,并将他的归属代理修改为此（开户绑定关系的代理）
					agentneed.setOneMerId(merId);
					agentService.update(agentneed);
				}

			}
		}
	}
	//给代理配置通道费率
	public void openFee(String merId, String merChantId){
		AgentLevelMerType levelType = new AgentLevelMerType();
		levelType.setAgentLevel("1");
		//查询代理等级对应的商户等级
		AgentLevelMerType agentmerType = agentLevelMerTypeService.findByObject(levelType);
		//升级代理商，默认将代理费的通道费率设置入表（费率来自商户通道表）
		MerChantsRate merrate = new MerChantsRate();
		merrate.setAppId("0000");
		merrate.setMerType(agentmerType.getMerType());
		List<MerChantsRate> rateList =  merchantsRateService.queryObjectForList(merrate);
		for(MerChantsRate rate:rateList){
			//无卡的
			if(rate.getIsRepayment().equals("N")){
				AgentRate agentRate = new AgentRate();
				agentRate.setMerChantId(merChantId);
				agentRate.setAgentId(merId);
				agentRate.setRate(rate.getRate());
				agentRate.setD0Fee(rate.getD0Fee());
				agentRate.setAisleCode(rate.getAisleCode());
				agentRateService.save(agentRate);

			} else if(rate.getIsRepayment().equals("Y")){//还款的
				Rates rates = new Rates();
				rates.setMerChantId(merChantId);
				rates.setAgentId(merId);
				rates.setRate(rate.getRate());
				rates.setD0Fee(rate.getD0Fee());
				rates.setAisleCode(rate.getAisleCode());
				ratesService.save(rates);
			}
		}
	}
	public void daizouMer(String merChantId, String merId, MerChants newMer){
		//查询此商户是不是别人的直推、如果是更改这些商户的代理商号为此商户的
				MerChants onem = new MerChants();
				onem.setOneMerId(merChantId);
				onem.setAgentStatus("N");
				List<MerChants> onelist = merChantsService.getGainList(onem);
				if(onelist.size() > 0){
					for(MerChants onemo: onelist){
						if(newMer.getAgentId().equals(onemo.getAgentId())){
							onemo.setAgentId(merId);
							merChantsService.update(onemo);
						}
					}
				}
				//查询此商户是不是别人的间推、如果是更改这些商户的代理商号为此商户的
				MerChants twom = new MerChants();
				twom.setTwoMerId(merChantId);
				twom.setAgentStatus("N");
				List<MerChants> twolist = merChantsService.getGainList(twom);
				if(twolist.size() > 0){
					for(MerChants twomo: twolist){
						if(newMer.getAgentId().equals(twomo.getAgentId())){
							twomo.setAgentId(merId);
							merChantsService.update(twomo);
						}
					}
				}
				//查询此商户是不是别人的间间推。如果是更改这些商户的代理商号为此商户的
				MerChants threem = new MerChants();
				threem.setThreeMerId(merChantId);
				threem.setAgentStatus("N");
				List<MerChants> threelist = merChantsService.getGainList(threem);
				if(threelist.size() > 0){
					for(MerChants threemo: threelist){
						if(newMer.getAgentId().equals(threemo.getAgentId())){
							threemo.setAgentId(merId);
							merChantsService.update(threemo);
						}
					}
				}
	}
}
