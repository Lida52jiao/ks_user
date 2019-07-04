package com.yjkj.ks_user.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.CardInformation;
import com.yjkj.ks_user.mapper.CardInformationMapper;
import com.yjkj.ks_user.service.CardInformationService;
import com.yjkj.ks_user.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardInformationServiceImpl extends
        BaseServiceImpl<CardInformation> implements CardInformationService {

	@Autowired
	private CardInformationMapper cardInformationMapper;

	public int find(String token, CardInformation cardInformation) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("token", token);
		param.put("merchantId", cardInformation.getMerChantId());
		param.put("state", "2");
		param.put("cardNo", cardInformation.getCardNumber());
		String resultJsonStr = HttpClientUtils.doPost(Constaint.HOST, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		String respDesc=job.getString("data");
		JSONObject s = JSONObject.parseObject(respDesc);
		String list=s.getString("list");
		JSONArray t = JSONObject.parseArray(list);
		return t.size();
	}

	public int gains(String merChantId) {
		int credit = cardInformationMapper.get(merChantId);
		return credit;
	}

	public int selectcard(String merChantId) {
		int card = cardInformationMapper.getcard(merChantId);
		return card;
	}

}
