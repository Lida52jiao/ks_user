package com.yjkj.ks_user.controller;

import com.alibaba.fastjson.JSONObject;
import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.*;
import com.yjkj.ks_user.service.*;
import com.yjkj.ks_user.util.HttpClientUtils;
import com.yjkj.ks_user.util.RedisUtils;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Attestation")
public class AttestationController extends BaseController {

	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private CardInformationService cardInformationService;
	@Autowired
	private NumService numService; 
	@Autowired
	private MerChantsRateService merchantsRateService;
	@Autowired
	private JiDaoService jiDaoService;
	@Autowired
	private ProfitService profitService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private RedisUtils redisUtils ;

	/*@RequestMapping(value = "initialize", method = RequestMethod.POST)
	public @ResponseBody
	YJResult initialize(String token, String merChantId, String name, String ID, String institutionId, String appId) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			if(!merChantId.equals(redisUtils.get(token))){
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败"); 
			}
			String transactionId = "YFB" + YJ.formatDate(new Date())
					+ YJ.formattime(new Date());
			RenZhen renZhen=merChantsService.getrenZhen(institutionId,appId);
			ZhimaCustomerCertificationInitializeRequest req = new ZhimaCustomerCertificationInitializeRequest();
			req.setChannel("apppc");
			req.setPlatform("zmop");
			req.setTransactionId(transactionId);// 必要参数
			req.setProductCode("w1010100000000002978");// 必要参数
			req.setBizCode("FACE");// 必要参数
			req.setIdentityParam("{\"identity_type\": \"CERT_INFO\", \"cert_type\": \"IDENTITY_CARD\", \"cert_name\": \""
					+ URLDecoder.decode(name) + "\", \"cert_no\":\"" + ID + "\"}");// 必要参数
			req.setMerchantConfig("{\"need_user_authorization\":\"false\"}");
			req.setExtBizParam("{}");// 必要参数
			DefaultZhimaClient client = new DefaultZhimaClient(renZhen.getGatewayUrl(), renZhen.getAppIds(),
					renZhen.getPrivateKey(), renZhen.getZhimaPublicKey());
			try {
				ZhimaCustomerCertificationInitializeResponse response = client
						.execute(req);
				// RedisUtils.returnResource(jedis);
				return YJResult.ok(response.getBizNo());
			} catch (ZhimaApiException e) {
				e.printStackTrace();
			}
			// RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.FAIL_INIT, "初始化错误");
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");	
	}

	@RequestMapping(value = "certify", method = RequestMethod.POST)
	public @ResponseBody
	YJResult certify(String token, String merChantId, String BizNo, String institutionId, String appId) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			if(!merChantId.equals(redisUtils.get(token))){
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败"); 
			}
			RenZhen renZhen=merChantsService.getrenZhen(institutionId,appId);
			ZhimaCustomerCertificationCertifyRequest req = new ZhimaCustomerCertificationCertifyRequest();
			req.setChannel("apppc");
			req.setPlatform("zmop");
			req.setBizNo(BizNo);// 必要参数
			req.setReturnUrl(renZhen.getBack());// 必要参数
			DefaultZhimaClient client = new DefaultZhimaClient(renZhen.getGatewayUrl(), renZhen.getAppIds(),
					renZhen.getPrivateKey(), renZhen.getZhimaPublicKey());
			try {
				String url = client.generatePageRedirectInvokeUrl(req);
				// RedisUtils.returnResource(jedis);
				return YJResult.ok(url);
			} catch (ZhimaApiException e) {
				e.printStackTrace();
			}
			// RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.FAIL_ZHIMA, "芝麻认证失败");
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
	YJResult query(String token, String merChantId, String merName, String certNo, String cardNumber, String issuingBank, String BizNo, String agentId, String institutionId, String appId) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			if(!merChantId.equals(redisUtils.get(token))){
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败"); 
			}
			RenZhen renZhen=merChantsService.getrenZhen(institutionId,appId);
			ZhimaCustomerCertificationQueryRequest req = new ZhimaCustomerCertificationQueryRequest();
			req.setChannel("apppc");
			req.setPlatform("zmop");
			req.setBizNo(BizNo);// 必要参数
			DefaultZhimaClient client = new DefaultZhimaClient(renZhen.getGatewayUrl(), renZhen.getAppIds(),
					renZhen.getPrivateKey(), renZhen.getZhimaPublicKey());
			try {
				ZhimaCustomerCertificationQueryResponse response = client
						.execute(req);
				if ("true".equals(response.getPassed())) {
					MerChants m = new MerChants();
					m.setMerChantId(merChantId);
					MerChants h = merChantsService.findByObject(m);
					h.setMerName(URLDecoder.decode(merName));
					h.setCertNo(certNo);
					h.setMerStat("Y");
					h.setMerStatTime(System.currentTimeMillis()+"");
					h.setFrozen("Y");
					h.setStartDate(System.currentTimeMillis()+"");
					h.setFinishDate("1514706437000");
					merChantsService.update(h);
					CardInformation n=new CardInformation(merChantId, "SC", cardNumber, "", issuingBank, "", "", "", "", "", "Y", System.currentTimeMillis()+"", "");
					n.setAgentId(agentId);
					n.setInstitutionId(institutionId);
					n.setAppId(appId);
					cardInformationService.save(n);
					// RedisUtils.returnResource(jedis);
					return YJResult.ok(response.getPassed());
				}	
			} catch (ZhimaApiException e) {
				e.printStackTrace();
			}
			// RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.FAIL_FOUND, "查询失败");
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public @ResponseBody
	YJResult finds(String token, String merChantId, String merName, String certNo, String BizNo, String institutionId, String appId) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			if(!merChantId.equals(redisUtils.get(token))){
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败"); 
			}
			RenZhen renZhen=merChantsService.getrenZhen(institutionId,appId);
			ZhimaCustomerCertificationQueryRequest req = new ZhimaCustomerCertificationQueryRequest();
			req.setChannel("apppc");
			req.setPlatform("zmop");
			req.setBizNo(BizNo);// 必要参数
			DefaultZhimaClient client = new DefaultZhimaClient(renZhen.getGatewayUrl(), renZhen.getAppIds(),
					renZhen.getPrivateKey(), renZhen.getZhimaPublicKey());
			try {
				ZhimaCustomerCertificationQueryResponse response = client
						.execute(req);
				if ("true".equals(response.getPassed())) {
					MerChants m = new MerChants();
					m.setMerChantId(merChantId);
					MerChants h = merChantsService.findByObject(m);
					h.setMerName(URLDecoder.decode(merName));
					h.setCertNo(certNo);
					h.setMerStat("Y");
					h.setMerStatTime(System.currentTimeMillis()+"");
					//h.setFrozen("Y");
					//h.setStartDate(System.currentTimeMillis()+"");
					//h.setFinishDate("1735660800000");
					merChantsService.update(h);
					// RedisUtils.returnResource(jedis);
					return YJResult.ok(response.getPassed());
				}	
			} catch (ZhimaApiException e) {
				e.printStackTrace();
			}
			// RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.FAIL_FOUND, "查询失败");
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "select", method = RequestMethod.POST)
	public @ResponseBody
	YJResult find(String token, String merChantId, String merName, String certNo, String BizNo, String institutionId, String appId) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			if(!merChantId.equals(redisUtils.get(token))){
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败"); 
			}
			RenZhen renZhen=merChantsService.getrenZhen(institutionId,appId);
			ZhimaCustomerCertificationQueryRequest req = new ZhimaCustomerCertificationQueryRequest();
			req.setChannel("apppc");
			req.setPlatform("zmop");
			req.setBizNo(BizNo);// 必要参数
			DefaultZhimaClient client = new DefaultZhimaClient(renZhen.getGatewayUrl(), renZhen.getAppIds(),
					renZhen.getPrivateKey(), renZhen.getZhimaPublicKey());
			try {
				ZhimaCustomerCertificationQueryResponse response = client
						.execute(req);
				if ("true".equals(response.getPassed())) {
					MerChants m = new MerChants();
					m.setMerChantId(merChantId);
					MerChants h = merChantsService.findByObject(m);
					h.setMerName(URLDecoder.decode(merName));
					h.setCertNo(certNo);
					h.setMerStat("Y");
					h.setMerStatTime(System.currentTimeMillis()+"");
					h.setFrozen("Y");
					//h.setStartDate(System.currentTimeMillis()+"");
					h.setFinishDate("1735660800000");
					merChantsService.update(h);
					merChantsService.send(h);
					MerChants v = new MerChants();
					v.setMerChantId(h.getOneMerId());
					v.setMerStat("Y");
					int n=merChantsService.statistics(v);
					MerChants t = new MerChants();
					t.setMerChantId(h.getOneMerId());
					MerChants merChants = merChantsService.findByObject(t);
					Num num = numService.findByPrimaryKey((long)2);
					Num tt = numService.findByPrimaryKey((long)3);
					if(n>=Integer.parseInt(num.getAmount()) & n<Integer.parseInt(tt.getAmount())){
						if(Integer.parseInt(merChants.getMerType())<2){
							merChants.setMerType("2");
							merChants.setMerChantFee("0.60");
							merChantsService.update(merChants);	
							MerChantsRate merChantsRate = new MerChantsRate();
							merChantsRate.setMerType("2");
							merChantsRate.setAppId(merChants.getAppId());
							List<MerChantsRate> list = merchantsRateService.queryObjectForList(merChantsRate);
							merchantsRateService.find(merChants.getMerChantId(),merChants.getAppId(),merChants.getInstitutionId(),list);
							MerChantsRate merChantsRates = new MerChantsRate();
							merChantsRates.setMerType("2");
							merChantsRates.setAisleCode("easy");
							merChantsRate.setAppId(merChants.getAppId());
							MerChantsRate merChant = merchantsRateService.findByObject(merChantsRates);
							if(null != merChants){
								merchantsRateService.alter(merChants,merChant);
							}
						}
					}
					// RedisUtils.returnResource(jedis);
					return YJResult.ok(response.getPassed());
				}	
			} catch (ZhimaApiException e) {
				e.printStackTrace();
			}
			// RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.FAIL_FOUND, "查询失败");
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}*/
	
	//实名认证
	@RequestMapping(value = "attestation")
	public @ResponseBody
	YJResult attestation(String token, String merChantId, String merName, String certNo, String cardNumber, String institutionId, String appId) {
		if(redisUtils.exists(token)){
			if(!merChantId.equals(redisUtils.get(token))){
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			MerChants merChant = new MerChants();
			merChant.setMerChantId(merChantId);
			MerChants h = merChantsService.findByObject(merChant);
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("merchantId", merChantId);
			hashMap.put("institutionId", h.getInstitutionId());
			hashMap.put("type", "sys");
			hashMap.put("name", URLDecoder.decode(merName));
			hashMap.put("card", cardNumber);
			hashMap.put("certNo", certNo);
			String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/check", hashMap);
			com.alibaba.fastjson.JSONObject job = com.alibaba.fastjson.JSONObject.parseObject(resultJsonStr);
			if("0000".equals(job.getString("respCode"))){
				String data = job.getString("data");
				com.alibaba.fastjson.JSONObject jobs = com.alibaba.fastjson.JSONObject.parseObject(data);
				//RedisUtils.returnResource(jedis);
				String result = jobs.getString("result");
				com.alibaba.fastjson.JSONObject s = com.alibaba.fastjson.JSONObject.parseObject(result);
				String status = s.getString("status");
			if("S".equals(status)){
				h.setMerName(merName);
				h.setCertNo(certNo);
				h.setMerStat("Y");
				h.setMerStatTime(System.currentTimeMillis()+"");
				Num num = new Num();
				num.setPayType("vip");
				Num t = numService.findByObject(num);
				if("0".equals(t.getNum())){
					long startDate=0;
					long finishDate=0;
					finishDate=(long) Long.parseLong(t.getValidTime())*24*3600*1000;
					h.setFrozen("Y");
					startDate= System.currentTimeMillis();
					h.setStartDate(startDate+"");
					h.setFinishDate(startDate+finishDate+"");
				}
				h.setUsed("N");
				List<Area> tList = getArea(h);
				if(!tList.isEmpty()){
					for(Area area : tList){
						Area areas = (Area)area;
						if(null != areas){
							if("3".equals(areas.getLevel())){
								h.setCounty(areas.getCode());
							}else if("2".equals(areas.getLevel())){
								h.setCity(areas.getCode());
							}else if("1".equals(areas.getLevel())){
								h.setProvince(areas.getCode());
							}
						}
					}
				}
				merChantsService.update(h);
				MerChants v = new MerChants();
				v.setMerChantId(h.getOneMerId());
				v.setMerStat("Y");
				int n=merChantsService.statistics(v);
				MerChants ss = new MerChants();
				ss.setMerChantId(h.getOneMerId());
				MerChants merChants = merChantsService.findByObject(ss);
				Num k = new Num();
				k.setAmount(n+"");
				Num w = numService.findByObject(k);
				if(w != null){
					merChants.setMerType(w.getMerType());
					merChantsService.update(merChants);
					Profit d = new Profit();
					d.setInstitutionId(h.getInstitutionId());
					Profit profit = profitService.findByObject(d);
					if("rate".equals(profit.getProfitModel())){
						MerChantsRate merChantsRate = new MerChantsRate();
						merChantsRate.setMerType(w.getMerType());
						merChantsRate.setIsRepayment("N");
						merChantsRate.setAppId(merChants.getAppId());
						List<MerChantsRate> list = merchantsRateService.queryObjectForList(merChantsRate);
						merchantsRateService.find(merChants.getMerChantId(),merChants.getAppId(),merChants.getInstitutionId(),list);
					}
				}
				try{
					merchantsRateService.send(h);
				}catch(Exception e){
					
				}
				try{
					merChantsService.sends(h);
				}catch(Exception e){
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("merChantId", merChantId);
				map.put("merName", merName);
				map.put("certNo", certNo);
				map.put("cardNumber", cardNumber);
				map.put("status", status);
				// RedisUtils.returnResource(jedis);
				return YJResult.ok(map);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("merChantId", merChantId);
			map.put("merName", merName);
			map.put("certNo", certNo);
			map.put("cardNumber", cardNumber);
			map.put("status", status);
			// RedisUtils.returnResource(jedis);
			return YJResult.ok(map);
		}
			return YJResult.build(job.getString("respCode"), job.getString("respDesc"));

		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	//获取省市县
	List<Area> getArea(MerChants h) {
		String s = h.getCertNo().substring(0, 6);
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
			if(n != null){
				Area b = new Area(); 
				b.setId(Long.parseLong(n.getParent_id()));
				Area tt = areaService.findByObject(b);
				list.add(tt);
			}
		}
		return list;
		}
	
	/*@RequestMapping(value = "attestations", method = RequestMethod.POST)
	public @ResponseBody
	YJResult attestations(String token, String merChantId, String merName, String certNo, String cardNumber, String institutionId, String appId) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			if(!merChantId.equals(redisUtils.get(token))){
				// RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败"); 
			}
			MerChants m=new MerChants();
			m.setMerChantId(merChantId); 
			MerChants t=merChantsService.findByObject(m);
			if(null != t){
				JiDao jidao = new JiDao(merChantId, merName, cardNumber, System.currentTimeMillis()+"");
				jidao.setAppId(t.getAppId());
				jiDaoService.save(jidao);
			}
			String url="http://www.jidaoyouxin.com/api/v1/card_authentication";
			JSONObject data=new JSONObject();
			data.put("type", "three");
			data.put("name", merName);
			data.put("card", cardNumber);
			data.put("id_card", certNo);
			String resultJsonStr = HttpClientUtils.doPostJson(url, data.toJSONString());
			System.out.println(resultJsonStr);
			JSONObject job = JSONObject.parseObject(resultJsonStr);
			String result = job.getString("result");
			JSONObject s = JSONObject.parseObject(result);
			String status = s.getString("status");
			if("S".equals(status)){
				MerChants merChant = new MerChants();
				merChant.setMerChantId(merChantId);
				MerChants h = merChantsService.findByObject(merChant);
				h.setMerName(merName);
				h.setCertNo(certNo);
				h.setMerStat("Y");
				h.setMerStatTime(System.currentTimeMillis()+"");
				h.setFrozen("Y");
				//h.setStartDate(System.currentTimeMillis()+"");
				h.setFinishDate("1735660800000");
				h.setUsed("N");
				merChantsService.update(h);
				merChantsService.send(h);
				MerChants v = new MerChants();
				v.setMerChantId(h.getOneMerId());
				v.setMerStat("Y");
				int n=merChantsService.statistics(v);
				MerChants ss = new MerChants();
				ss.setMerChantId(h.getOneMerId());
				MerChants merChants = merChantsService.findByObject(ss);
				Num num = numService.findByPrimaryKey((long)2);
				Num tt = numService.findByPrimaryKey((long)3);
				if(n>=Integer.parseInt(num.getAmount()) & n<Integer.parseInt(tt.getAmount())){
					if(Integer.parseInt(merChants.getMerType())<2){
						merChants.setMerType("2");
						merChants.setMerChantFee("0.60");
						merChantsService.update(merChants);	
						MerChantsRate merChantsRate = new MerChantsRate();
						merChantsRate.setMerType("2");
						merChantsRate.setAppId(merChants.getAppId());
						List<MerChantsRate> list = merchantsRateService.queryObjectForList(merChantsRate);
						merchantsRateService.find(merChants.getMerChantId(),merChants.getAppId(),merChants.getInstitutionId(),list);
						MerChantsRate merChantsRates = new MerChantsRate();
						merChantsRates.setMerType("2");
						merChantsRates.setAisleCode("easy");
						merChantsRate.setAppId(merChants.getAppId());
						MerChantsRate merChant = merchantsRateService.findByObject(merChantsRates);
						if(null != merChants){
							merchantsRateService.alter(merChants,merChant);
						}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("merChantId", merChantId);
				map.put("merName", merName);
				map.put("certNo", certNo);
				map.put("cardNumber", cardNumber);
				map.put("status", status);
				// RedisUtils.returnResource(jedis);
				return YJResult.ok(map);
			}
				}
			}		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("merChantId", merChantId);
			map.put("merName", merName);
			map.put("certNo", certNo);
			map.put("cardNumber", cardNumber);
			map.put("status", status);
			// RedisUtils.returnResource(jedis);
			return YJResult.ok(map);
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}*/
	
}
