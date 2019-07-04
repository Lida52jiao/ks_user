package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.Code;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.entity.MerCode;
import com.yjkj.ks_user.entity.Transaction;
import com.yjkj.ks_user.mapper.CodeMapper;
import com.yjkj.ks_user.mapper.MerChantsMapper;
import com.yjkj.ks_user.mapper.MerCodeMapper;
import com.yjkj.ks_user.mapper.TransactionMapper;
import com.yjkj.ks_user.service.CodeService;
import com.yjkj.ks_user.util.MD5Util;
import com.yjkj.ks_user.util.RedisUtils;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeServiceImpl extends BaseServiceImpl<Code> implements CodeService {
	
	@Autowired
	private CodeMapper codeMapper;
	@Autowired
	private TransactionMapper transactionMapper;
	@Autowired
	private MerCodeMapper merCodeMapper;
	@Autowired
	private MerChantsMapper merChantsMapper;
	
	public Map<String, Object> receive(Integer page, Integer rows,
                                       String agentId, String status) {
		if(page==null){
			page=1;
		}
		if(rows==null){
			rows=10;
		}
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("sindex", (page-1)*rows);
		m.put("eindex", rows);
		m.put("agentId", agentId);
		m.put("status", status);
		List<Code> n=codeMapper.get(m);
		int total=codeMapper.selectCode(m);
		
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("rows", n);
		result.put("total", total);
		result.put("totalPages", total%rows==0?total/rows:(total/rows+1));
		result.put("currentPage", page);
		result.put("pageSize", rows);
		
		return result;
	}
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public YJResult find(Jedis jedis, String agentId) {
		Transaction transaction = new Transaction();
		transaction.setMerId(agentId);
		Transaction f = transactionMapper.selectOne(transaction);
		if(f == null){
			// RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.NONE_FLOOR, "无结算底价,生成激活码失败"); 
		}
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
			code.setStatus("N");
			codeMapper.insert(code);
			// RedisUtils.returnResource(jedis);
			return YJResult.ok(code);
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.NOT_CODE, "激活码不足");
	}
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public YJResult get(Jedis jedis, String merChantId, String activationCode) {
		Code code = new Code();
		code.setActivationCode(activationCode);
		Code n = codeMapper.selectOne(code);
		MerChants merChants = new MerChants();
		merChants.setMerChantId(merChantId); 
		MerChants s = merChantsMapper.selectOne(merChants);
		if(n != null){
			if(!n.getAgentId().equals(s.getAgentId())){
				// RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.USED, "请使用归属代理商的激活码");
			}
			if("Y".equals(n.getStatus())){
				// RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.USED, "激活码已被使用");
			}
			n.setMerChantId(merChantId);
			n.setActDate(System.currentTimeMillis()+"");
			n.setStatus("Y");
			n.setMerMp(s.getMerMp());
			codeMapper.updateByPrimaryKey(n);
			merCodeMapper.getLock(n.getAgentId());
			int used = 1;
			int notused = -1;
			merCodeMapper.revises(n.getAgentId(),used,notused);
			s.setFrozen("Y");
			s.setStartDate(System.currentTimeMillis()+"");
			s.setFinishDate("1735660800000");
			merChantsMapper.updateByPrimaryKey(s);
			// RedisUtils.returnResource(jedis);
			return YJResult.ok();
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.FAIL, "无此激活码");
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public YJResult finds(String agentId) {
		Transaction transaction = new Transaction();
		transaction.setMerId(agentId);
		Transaction f = transactionMapper.selectOne(transaction);
		if(f == null){
			return YJResult.build(Constaint.NONE_FLOOR, "无结算底价,生成激活码失败"); 
		}
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
			code.setStatus("N");
			codeMapper.insert(code);
			return YJResult.ok(code);
		}
		return YJResult.build(Constaint.NOT_CODE, "激活码不足");
	}

}
