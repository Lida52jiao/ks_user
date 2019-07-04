package com.yjkj.ks_user.controller;

import com.alibaba.fastjson.JSONObject;
import com.yjkj.ks_user.entity.Agent;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.entity.PaymentNum;
import com.yjkj.ks_user.entity.Record;
import com.yjkj.ks_user.service.*;
import com.yjkj.ks_user.util.SignUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/KSPaymentCallBack/")
public class KSPaymentCallBackCtl {

	private static final Log logger = LogFactory.getLog(KSPaymentCallBackCtl.class);
	
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private KSPaymentNumService ksPaymentNumService;
	@Autowired
	private RecordService recordService;
	@Autowired
	private KSAgentRateTableSAervice ksAgentRateTableSAervice;
	@Autowired
	private AgentService agentService;
	@Autowired
	private UserService userService;
	@Autowired
	private MerCodeService merCodeService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private KSUpAgentLevelService ksUpAgentLevelService;
	
	@RequestMapping(value = "callBack",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String changeMerchants(String merChantId, String agentId, String payType, BigDecimal amount, String orderNo, String sign){
		logger.info("卡神管家回调参数为："+"商户号："+merChantId+"代理号："+agentId+"支付标识："+payType+"金额："+amount+"订单号："+orderNo);
		LinkedHashMap<String,Object> merHashMap=new LinkedHashMap<String, Object>();
		merHashMap.put("merChantId",merChantId);
		merHashMap.put("agentId",agentId);
		merHashMap.put("amount",amount);
		merHashMap.put("payType",payType);
		merHashMap.put("orderNo",orderNo);
		String merSign= SignUtil.createYJSign(merHashMap);
		//验签
		if(!merSign.equals(sign)){
			logger.info("签名错误");
			return "signError";
		}
		//查询是否缴纳过升级费
		Record re = new Record();
		re.setOrderNo(orderNo);
		Record record = recordService.findByObject(re);
		if(record != null){
			logger.info("已缴纳过费用");
			return "payAready";
		}
		//查询商户信息
		MerChants mer = new MerChants();
		mer.setMerChantId(merChantId);
		MerChants newMer = merChantsService.findByObject(mer);//查询商户信息
		String oneMerId = newMer.getOneMerId();//直推
		String twoMerId = newMer.getTwoMerId();//间推
		String threeMerId = newMer.getThreeMerId();//间间推
		String agentLevel = "1";
		//查询代理信息直接代理
		Agent agent = new Agent();
		agent.setMerId(newMer.getAgentId());
		Agent newAgent = agentService.gain(agent);
		logger.info("直接代理信息："+JSONObject.toJSONString(newAgent));
		String AllMessageOne = "直推"+oneMerId+"间推"+twoMerId+"间间推"+threeMerId+"直接代理"+newMer.getAgentId()+"间接代理"+newAgent.getOneMerId();
		List<PaymentNum> list = ksPaymentNumService.getAll(payType);//查询升级模式，缴费缴的哪一级别
		if(list.size() != 0){
			PaymentNum payment = list.get(0);
			logger.info("缴费信息为："+JSONObject.toJSONString(payment));
			String agentOrNot = payment.getAgentOrNot();//缴费标识是否为代理商
			//缴费商户直接升商户等级
			if("N".equals(agentOrNot)){
				Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "会员升级费用");
				recordService.save(s);
				newMer.setMerType("2");
				merChantsService.update(newMer);
				return "success";
			}
			//缴费升级代理费
			//缴费代理升级代理等级并升级商户等级
			Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "代理升级费用"+AllMessageOne);
			recordService.save(s);
			//去升级代理等级
			String message = ksUpAgentLevelService.upAgentLevel(newMer,merChantId,oneMerId);
			return message;
		}

		return "";
	}
}
