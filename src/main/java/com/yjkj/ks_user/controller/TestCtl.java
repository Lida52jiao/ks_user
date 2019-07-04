package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.CardInformation;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.entity.PaymentNum;
import com.yjkj.ks_user.task.BankCode;
import com.yjkj.ks_user.util.HttpClientUtils;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

public class TestCtl {

//	public static void main(String[] args) {
//		LinkedHashMap<String,Object> merHashMap=new LinkedHashMap<String, Object>();
//		merHashMap.put("merChantId","M47864028366452736010068");
//		merHashMap.put("agentId","12");
//		merHashMap.put("amount","20");
//		merHashMap.put("payType","bigShot");
//		merHashMap.put("orderNo","12");
//		String merSign= SignUtil.createYJSign(merHashMap);
//		System.out.println(merSign);
//		//97ed7b2926e9a8b4dfe48ad787715775
//	}
//	public static void main(String[] args) {
//		LinkedHashMap<String,Object> merHashMap=new LinkedHashMap<String, Object>();
//		merHashMap.put("merChantId","M47864028366452736010068");
//		merHashMap.put("agentId","12");
//		merHashMap.put("amount","20");
//		merHashMap.put("payType","agentShot");
//		merHashMap.put("orderNo","13");
//		String merSign= SignUtil.createYJSign(merHashMap);
//		System.out.println(merSign);
//		bb530fe5308ac615a987ff2c3986e76c
//	}
	public static void main(String[] args) {
		MerChants merChants = new MerChants();
		merChants.setMerName("石桐旭");
		CardInformation card = new CardInformation();
		card.setCardNumber("4984511294270382");
		card.setIssuingBank("SPDB");
		String msg = "【卡神】尊敬的"+merChants.getMerName()+"先生/女士，您尾号为"+card.getCardNumber().substring(card.getCardNumber().length()-4)+"的"+ BankCode.getName(card.getIssuingBank())+"的信用卡已经出账单了哦，可以来制定计划了，我们将为您打造完美的账单。";
		Map<String, String > map = new HashedMap();
		map.put("merChantId", merChants.getMerChantId());
		map.put("appId", merChants.getAppId());
		map.put("institutionId", merChants.getInstitutionId());
		map.put("msg", msg);
		map.put("type", "1");
//		HttpClientUtils.doPost("http://47.104.25.59/templet/JiGuang/transmission.shtml", map);
		Map<String,String> mmap = new HashedMap();
		mmap.put("merchantId", "M48659187453946675226241");
		mmap.put("mobile", "13522337211");
		mmap.put("institutionId", "T00000009");
		mmap.put("msg", msg);
		mmap.put("type", "dx");
		System.out.println(msg);
//		HttpClientUtils.doPost("http://47.104.4.155:1172/account/remind", mmap);

	}
}
