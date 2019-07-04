package com.yjkj.ks_user.controller;//package com.yjkj.com.yjkj.ks_user.controller;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yjkj.com.yjkj.ks_user.entity.Agent;
//import com.yjkj.com.yjkj.ks_user.entity.AgentRate;
//import com.yjkj.com.yjkj.ks_user.entity.KSAgentRateTable;
//import com.yjkj.com.yjkj.ks_user.entity.MerChants;
//import com.yjkj.com.yjkj.ks_user.entity.MerCode;
//import com.yjkj.com.yjkj.ks_user.entity.PaymentNum;
//import com.yjkj.com.yjkj.ks_user.entity.Rates;
//import com.yjkj.com.yjkj.ks_user.entity.Record;
//import com.yjkj.com.yjkj.ks_user.entity.Transaction;
//import com.yjkj.com.yjkj.ks_user.entity.UserEntity;
//import com.yjkj.com.yjkj.ks_user.service.AgentService;
//import com.yjkj.com.yjkj.ks_user.service.KSAgentRateTableSAervice;
//import com.yjkj.com.yjkj.ks_user.service.KSPaymentNumService;
//import com.yjkj.com.yjkj.ks_user.service.MerChantsService;
//import com.yjkj.com.yjkj.ks_user.service.MerCodeService;
//import com.yjkj.com.yjkj.ks_user.service.RecordService;
//import com.yjkj.com.yjkj.ks_user.service.TransactionService;
//import com.yjkj.com.yjkj.ks_user.service.UserService;
////import com.yjkj.com.yjkj.ks_user.service.UserService;
//import com.yjkj.com.yjkj.ks_user.util.PasswordHelper;
//import com.yjkj.com.yjkj.ks_user.util.SignUtil;
//import com.yjkj.com.yjkj.ks_user.util.YJ;
//
//@Controller
//@RequestMapping("/KSPaymentCallBack/")
//public class KSPaymentCallBackCtlTest {
//	private static final Log logger = LogFactory.getLog(KSPaymentCallBackCtlTest.class);
//	
//	@Autowired
//	private MerChantsService merChantsService;
//	@Autowired
//	private KSPaymentNumService ksPaymentNumService;
//	@Autowired
//	private RecordService recordService;
//	@Autowired
//	private KSAgentRateTableSAervice ksAgentRateTableSAervice;
//	@Autowired
//	private AgentService agentService;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private MerCodeService merCodeService;
//	@Autowired
//	public TransactionService transactionService;
//	
//	@RequestMapping("callBack")
//	public String changeMerchants(String merChantId, String agentId, String payType, BigDecimal amount, String orderNo, String sign){
//		logger.info("卡神回调参数为："+null);
//		LinkedHashMap<String,Object> merHashMap=new LinkedHashMap<String, Object>();
//		merHashMap.put("merChantId",merChantId);
//		merHashMap.put("agentId",agentId);
//		merHashMap.put("amount",amount);
//		merHashMap.put("payType",payType);
//		merHashMap.put("orderNo",orderNo);
//		String merSign= SignUtil.createYJSign(merHashMap);
//		//验证签名
//		if(sign.equals(merSign)){
//			MerChants mer = new MerChants();
//			mer.setMerChantId(merChantId);
//			MerChants newMer = merChantsService.findByObject(mer);//查询商户信息
//			String oneMerId = newMer.getOneMerId();//直推
//			String twoMerId = newMer.getTwoMerId();//间推
//			String threeMerId = newMer.getThreeMerId();//间间推
//			//查询代理信息
//			Agent agent = new Agent();
//			agent.setMerId(newMer.getAgentId());
//			Agent newAgent = agentService.gain(agent);
//			logger.info("第一次查询归属代理信息为："+JSONObject.toJSONString(newAgent));
//			//该商户不为空，查询该商户的直接代理间接代理
//			if(newMer != null){
//				//该商户直接代理不为空
//				if(newAgent != null){
//					//如果商户姓名与代理商姓名相同，则查询该代理的直接代理是谁（这块判断是因为，一个人先开代理以后，他的归属代理就是自己了）
//					if(newMer.getMerName().equals(newAgent.getMerName())){
//						Agent two = new Agent();
//						two.setMerId(newAgent.getOneMerId());
//						Agent twoAgent = agentService.gain(two);//直接代理
//						logger.info("商户姓名与代理商名相同第一次查询归属代理信息为："+JSONObject.toJSONString(twoAgent));
//						Agent three = new Agent();
//						three.setMerId(twoAgent.getOneMerId());
//						Agent threeAgent = agentService.gain(three);//间接代理
//						logger.info("商户姓名与代理商名相同第二次查询归属代理信息为："+JSONObject.toJSONString(threeAgent));
//						String AllMessageOne = "直推"+oneMerId+"间推"+twoMerId+"间间推"+threeMerId+"直接代理"+twoAgent.getMerId()+"间接代理"+threeAgent.getMerId();
//						List<PaymentNum> list = ksPaymentNumService.getAll(payType);//查询升级模式，缴费缴的哪一级别
//						if(list.size() != 0){
//							PaymentNum payment = list.get(0);
//							logger.info("缴费信息为："+JSONObject.toJSONString(payment));
//							String agentOrNot = payment.getAgentOrNot();//缴费标识是否为代理商
//							//缴费商户直接升商户等级
//							if("N".equals(agentOrNot)){
//								Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "会员升级费用"+AllMessageOne);//将直推、间推、间间推、直接代理、间接代理存储起来
//								recordService.save(s);
//								newMer.setMerType("2");
//								merChantsService.update(newMer);
//							}
//							//缴费代理升级代理等级并升级商户等级
//							Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "代理升级费用"+AllMessageOne);
//							recordService.save(s);
//							newMer.setAgentStatus("1");
//							newMer.setMerType("2");
//							merChantsService.update(newMer);
//							//省级代理商、进行开户、绑定关系操作
//							UserEntity userFormMap = new UserEntity();
//							userFormMap.setAccountName(newMer.getMerName());
//							userFormMap.setPassWord("12345");
//							UserEntity r=PasswordHelper.encryptPassword(userFormMap);
//							Agent n=new Agent(newMer.getMerName(), newMer.getMerMp(), newMer.getMerName(), r.getPassWord(), ".com", oneMerId, System.currentTimeMillis()+"");
//							agentService.save(n);
//							Agent h=agentService.findByObject(n);
//							String sagentId = "C" + YJ.formatDate(new Date()) + YJ.formattime(new Date());
//							long id = h.getId() + 10000;
//							String merId = sagentId + id;
//							h.setMerId(merId);
//							h.setTotalCode("0");
//							h.setAssign("0");
//							h.setGeneratedCode("0");
//							h.setUsed("0");
//							h.setNotused("0");
//							agentService.update(h);
//							MerCode merCode = new MerCode();
//							merCode.setMerId(merId);
//							merCode.setMerName(newMer.getMerName());
//							merCode.setOneMerId(oneMerId);
//							merCode.setTotalCode(0);
//							merCode.setGeneratedCode(0);
//							merCode.setUsed(0);
//							merCode.setNotused(0);
//							merCode.setAssign(0);
//							merCodeService.save(merCode);
//							UserEntity k=new UserEntity();
//							k.setAccountName(newMer.getMerName());
//							k.setDate(YJ.formatDate(new Date())+YJ.formattime(new Date()));
//							k.setEmail(".com");
//							k.setPassWord("12345");
//							k.setMerId(merId);
//							k.setTel(newMer.getMerMp());
//							k.setUserName(newMer.getMerName());
//							k.setLocked("1");
//							PasswordHelper.encryptPassword(k);
//							userService.save(k);
//							//代理商绑定关系
//							Transaction savetransation=new Transaction();
//							savetransation.setMerId(merId);
//							savetransation.setAgentName(newMer.getMerName());
//							savetransation.setMerChantId(merChantId);
//							savetransation.setCreatDate(System.currentTimeMillis()+"");
//							savetransation.setAgentStatus("1");
//							String message = transactionService.save(savetransation);
//							logger.info("绑定商户关系条数："+message);
//							//省级代理商，默认将代理费个通道费率设置入表
//							List<KSAgentRateTable> rateList = ksAgentRateTableSAervice.getAgentRateByLevel("1");
//							for(KSAgentRateTable rate:rateList){
//								//无卡的
//								if(rate.getIsRepayment().equals("N")){
//									AgentRate agentRate = new AgentRate();
//									agentRate.setMerChantId(merChantId);
//									agentRate.setAgentId(agentId);
//									agentRate.setRate(rate.getRate());
//									agentRate.setD0Fee(rate.getD0Fee());
//									agentRate.setAisleCode(rate.getAisleCode());
//								} else if(rate.getIsRepayment().equals("Y")){//还款的
//									Rates rates = new Rates();
//									rates.setMerChantId(merChantId);
//									rates.setAgentId(agentId);
//									rates.setRate(rate.getRate());
//									rates.setD0Fee(rate.getD0Fee());
//									rates.setAisleCode(rate.getAisleCode());
//								}
//								
//							}
//						}
//					}
//					return "success";
//				}
//			}
//			Agent four = new Agent();
//			four.setMerId(newMer.getAgentId());
//			Agent fourAgent = agentService.gain(agent);//商户姓名与代理名不同的间接代理
//			logger.info("第二次查询归属代理信息为："+JSONObject.toJSONString(fourAgent));
//			String AllMessageTwo = "直推"+oneMerId+"间推"+twoMerId+"间间推"+threeMerId+"直接代理"+newAgent.getMerId()+"间接代理"+fourAgent.getMerId();
//			List<PaymentNum> list = ksPaymentNumService.getAll(payType);//查询升级模式，缴费缴的哪一级别
//			if(list.size() != 0){
//				PaymentNum payment = list.get(0);
//				logger.info("缴费信息为："+JSONObject.toJSONString(payment));
//				String agentOrNot = payment.getAgentOrNot();//缴费标识是否为代理商
//				//缴费商户直接升商户等级
//				if("N".equals(agentOrNot)){
//					Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "会员升级费用"+AllMessageTwo);//将直推、间推、间间推、直接代理、间接代理存储起来
//					recordService.save(s);
//					newMer.setMerType("2");
//					merChantsService.update(newMer);
//				}
//				//缴费代理升级代理等级并升级商户等级
//				Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "代理升级费用"+AllMessageTwo);
//				recordService.save(s);
//				newMer.setAgentStatus("1");
//				newMer.setMerType("2");
//				merChantsService.update(newMer);
//				//省级代理商、进行开户、绑定关系操作
//				UserEntity userFormMap = new UserEntity();
//				userFormMap.setAccountName(newMer.getMerName());
//				userFormMap.setPassWord("12345");
//				UserEntity r=PasswordHelper.encryptPassword(userFormMap);
//				Agent n=new Agent(newMer.getMerName(), newMer.getMerMp(), newMer.getMerName(), r.getPassWord(), ".com", oneMerId, System.currentTimeMillis()+"");
//				agentService.save(n);
//				Agent h=agentService.findByObject(n);
//				String sagentId = "C" + YJ.formatDate(new Date()) + YJ.formattime(new Date());
//				long id = h.getId() + 10000;
//				String merId = sagentId + id;
//				h.setMerId(merId);
//				h.setTotalCode("0");
//				h.setAssign("0");
//				h.setGeneratedCode("0");
//				h.setUsed("0");
//				h.setNotused("0");
//				agentService.update(h);
//				MerCode merCode = new MerCode();
//				merCode.setMerId(merId);
//				merCode.setMerName(newMer.getMerName());
//				merCode.setOneMerId(oneMerId);
//				merCode.setTotalCode(0);
//				merCode.setGeneratedCode(0);
//				merCode.setUsed(0);
//				merCode.setNotused(0);
//				merCode.setAssign(0);
//				merCodeService.save(merCode);
//				UserEntity k=new UserEntity();
//				k.setAccountName(newMer.getMerName());
//				k.setDate(YJ.formatDate(new Date())+YJ.formattime(new Date()));
//				k.setEmail(".com");
//				k.setPassWord("12345");
//				k.setMerId(merId);
//				k.setTel(newMer.getMerMp());
//				k.setUserName(newMer.getMerName());
//				k.setLocked("1");
//				PasswordHelper.encryptPassword(k);
//				userService.save(k);
//				//代理商绑定关系
//				Transaction savetransation=new Transaction();
//				savetransation.setMerId(merId);
//				savetransation.setAgentName(newMer.getMerName());
//				savetransation.setMerChantId(merChantId);
//				savetransation.setCreatDate(System.currentTimeMillis()+"");
//				savetransation.setAgentStatus("1");
//				String message = transactionService.save(savetransation);
//				logger.info("绑定商户关系条数："+message);
//				//升级代理商，默认将代理费个通道费率设置入表
//				List<KSAgentRateTable> rateList = ksAgentRateTableSAervice.getAgentRateByLevel("1");
//				for(KSAgentRateTable rate:rateList){
//					//无卡的
//					if(rate.getIsRepayment().equals("N")){
//						AgentRate agentRate = new AgentRate();
//						agentRate.setMerChantId(merChantId);
//						agentRate.setAgentId(agentId);
//						agentRate.setRate(rate.getRate());
//						agentRate.setD0Fee(rate.getD0Fee());
//						agentRate.setAisleCode(rate.getAisleCode());
//					} else if(rate.getIsRepayment().equals("Y")){//还款的
//						Rates rates = new Rates();
//						rates.setMerChantId(merChantId);
//						rates.setAgentId(agentId);
//						rates.setRate(rate.getRate());
//						rates.setD0Fee(rate.getD0Fee());
//						rates.setAisleCode(rate.getAisleCode());
//					}
//					
//				}
//			}
//			return "success";
//		}
//		
//		logger.info("回调签名为："+sign);
//		logger.info("生成签名："+merSign);
//		logger.info("签名错误");
//		return "error";
//	}
//
//}
