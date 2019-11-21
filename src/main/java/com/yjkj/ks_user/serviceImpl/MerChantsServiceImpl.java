package com.yjkj.ks_user.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.*;
import com.yjkj.ks_user.mapper.*;
import com.yjkj.ks_user.service.MerChantsService;
import com.yjkj.ks_user.util.HttpClientUtils;
import com.yjkj.ks_user.util.MD5Util;
import com.yjkj.ks_user.util.SignUtil;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerChantsServiceImpl extends BaseServiceImpl<MerChants> implements MerChantsService {

	@Autowired
	private MerChantsMapper merChantsMapper;
	@Autowired
	private RecordMapper recordMapper;
	@Autowired
	private UsedMapper usedMapper;
	@Autowired
	private MerCodeMapper merCodeMapper;
	@Autowired
	private CodeMapper codeMapper;

	@Override
	public int statistics(MerChants v) {
		int n=merChantsMapper.gain(v);
		return n;
	}
	@Override
	public int receive(String merChantId) {
		int n=merChantsMapper.selectCode(merChantId);
		return n;
	}
	@Override
	public int find(String merChantId) {
		int n=merChantsMapper.get(merChantId);
		return n;
	}
	@Override
	public DuanXin getduanXin(String institutionId, String appId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("institutionId", institutionId);
		param.put("appId", appId);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.DuanXin, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		DuanXin duanXin=new DuanXin(job.get("accessKeyId").toString(), job.get("accessKeySecret").toString(), job.get("product").toString(), job.get("domain").toString(), job.get("autograph").toString(), job.get("templateCode").toString());
		return duanXin;
	}
	@Override
	public RenZhen getrenZhen(String institutionId, String appId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("institutionId", institutionId);
		param.put("appId", appId);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.RenZhen, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		RenZhen renZhen=new RenZhen(job.get("gatewayUrl").toString(), job.get("appIds").toString(), job.get("privateKey").toString(), job.get("zhimaPublicKey").toString(), job.getString("back").toString());
		return renZhen;
	}
	@Override
	public MerChant gain(MerChant t) {
		MerChant s = merChantsMapper.receive(t);
		return s;
	}
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public YJResult get(String merChantId, String agentId, BigDecimal amount, String orderNo) {
		Record record = new Record();
		record.setOrderNo(orderNo);
		Record records = recordMapper.selectOne(record);
		if(null != records){
			return YJResult.build(Constaint.SUCCESS, "");
		}
		Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "激活码费");
		recordMapper.insert(s);
		Used t = usedMapper.selectByPrimaryKey((long)1);
		/*MerChants m = new MerChants();
		m.setMerChantId(merChantId);
		MerChants h = merChantsService.findByObject(m);*/
		if(new BigDecimal(t.getMonth()).compareTo(amount)==0){
			merCodeMapper.getLock(agentId);
			MerCode merCode = new MerCode();
			merCode.setMerId(agentId);
			MerCode merCodes = merCodeMapper.get(merCode);
			if(merCodes.getAssign() > 0){
				int generatedCode = 1;
				int notused = 1;
				int assign = -1;
				merCodeMapper.alter(agentId,generatedCode,notused,assign);
				Long k = System.currentTimeMillis();
				String string = agentId+k;
				String activationCode = MD5Util.getMD5String(string);
				Code code = new Code();
				code.setActivationCode(activationCode);
				code.setAgentId(agentId);
				code.setCreatDate(System.currentTimeMillis()+"");
				code.setOrderNo(orderNo);
				code.setStatus("N");
				codeMapper.insert(code);
				return YJResult.build(Constaint.SUCCESS, "");
			}
			Code code = new Code();
			code.setAgentId(agentId);
			code.setCreatDate(System.currentTimeMillis()+"");
			code.setOrderNo(orderNo);
			code.setRemarks("激活码数量不足");
			codeMapper.insert(code);
			return YJResult.build(Constaint.NOT_CODE, "激活码数量不足");
		}
		Code code = new Code();
		code.setAgentId(agentId);
		code.setCreatDate(System.currentTimeMillis()+"");
		code.setOrderNo(orderNo);
		code.setRemarks("金额不匹配");
		codeMapper.insert(code);
		return YJResult.build(Constaint.NOT_CODE, "金额不匹配");
	}
	@Override
	public void send(MerChants h) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("merchantId", h.getMerChantId());
		param.put("oneMerId", h.getOneMerId());
		param.put("appId", h.getAppId());
		param.put("institutionId", h.getInstitutionId());
		param.put("timestamp", System.currentTimeMillis());
		String aisleSign= SignUtil.createYJSign(param);
		param.put("sign",aisleSign);
		String resultJsonStr = HttpClientUtils.doPosts(Constaint.S, param);
	}
	@Override
	public void tran(String merName, String institutionId, String appId,
                     String phone, String type) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("merName", merName);
		param.put("institutionId", institutionId);
		param.put("appId", appId);
		param.put("phone", phone);
		param.put("type", type);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.HY, param);
	}
	@Override
	public List<MerChants> getGainList(MerChants mer) {
		return merChantsMapper.gainList(mer);
	}
	@Override
	public void bind(MerChants k) {
	  Map<String, String> param = new HashMap<>();
	  param.put("merchantId", k.getOneMerId());
	  param.put("msg", k.getMerMp());
	  param.put("type", "3");
	  param.put("style", "1");
	  param.put("agentId", k.getAgentId());
	  param.put("institutionId", Constaint.AGENT);
	  param.put("appId", k.getAppId());
	  String resultJsonStr = HttpClientUtils.doPost(Constaint.Bind, param);
	  JSONObject job = JSONObject.parseObject(resultJsonStr);
	  System.out.println(job);
  }

	@Override
	public Integer countByMobile(String mobile) {
		return merChantsMapper.countByMobile(mobile);
	}

	@Override
	public List<MerChants> selectByMobile(String mobile) {
		MerChants m = new MerChants();
		m.setMerMp(mobile);
		return merChantsMapper.gainList(m);
	}

	@Override
  public void sends(MerChants h) {
	  Map<String, String> param = new HashMap<>();
	  param.put("merchantId", h.getOneMerId());
	  param.put("msg", h.getMerName());
	  param.put("type","3");
	  param.put("style","2");
	  param.put("agentId", h.getAgentId());
	  param.put("institutionId", Constaint.AGENT);
	  param.put("appId", h.getAppId());
	  String resultJsonStr = HttpClientUtils.doPost(Constaint.Bind, param);
	  JSONObject job = JSONObject.parseObject(resultJsonStr);
	  System.out.println(job);
  }
}
