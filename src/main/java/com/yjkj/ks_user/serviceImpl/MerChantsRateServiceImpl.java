package com.yjkj.ks_user.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.entity.MerChantsRate;
import com.yjkj.ks_user.mapper.MerChantsMapper;
import com.yjkj.ks_user.mapper.MerChantsRateMapper;
import com.yjkj.ks_user.service.MerChantsRateService;
import com.yjkj.ks_user.util.HttpClientUtils;
import com.yjkj.ks_user.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MerChantsRateServiceImpl extends BaseServiceImpl<MerChantsRate> implements MerChantsRateService {
	
	@Autowired
	private MerChantsRateMapper merchantsRateMapper;
	@Autowired
	private MerChantsMapper merChantsMapper;

	public void find(String merChantId, String appId, String institutionId, List<MerChantsRate> list) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("merchantId", merChantId);
		param.put("appId", appId);
		param.put("institutionId", institutionId);
		param.put("timestamp", System.currentTimeMillis());
		param.put("jsonStr", JSON.toJSONString(list));
		String aisleSign= SignUtil.createYJSign(param);
		param.put("sign",aisleSign);
		String resultJsonStr = HttpClientUtils.doPosts(Constaint.HOSTS, param);
	}

	public void alter(MerChants h, MerChantsRate merChantsRates) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("merchantId", h.getMerChantId());
		param.put("institutionId", h.getInstitutionId());
		param.put("appId", h.getAppId());
		param.put("rate", merChantsRates.getRate());
		param.put("d0Fee", merChantsRates.getD0Fee());
		param.put("aisleCode", merChantsRates.getAisleCode());
		String aisleSign= SignUtil.createYJSign(param);
		param.put("sign",aisleSign);
		String resultJsonStr = HttpClientUtils.doPosts(Constaint.BIND+"/"+merChantsRates.getAisleCode()+Constaint.BINDS, param);
	}

	public void send(MerChants h) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("merchantId", h.getOneMerId());
		param.put("institutionId", h.getInstitutionId());
		String resultJsonStr = HttpClientUtils.doPosts(Constaint.JIFEN, param);
		System.out.println(resultJsonStr);
	}

}
