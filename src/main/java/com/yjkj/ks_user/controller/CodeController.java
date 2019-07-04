package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.Code;
import com.yjkj.ks_user.service.*;
import com.yjkj.ks_user.util.RedisUtils;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Map;

@Controller
@RequestMapping("Code")
public class CodeController extends BaseController {
	
	@Autowired
	private CodeService codeService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private FeeService feeService;
	@Autowired
	private CreditService creditService;
	@Autowired
	private RedisUtils redisUtils ;
	
	@RequestMapping(value = "selectActivationCodeList")
	public @ResponseBody
	YJResult get(String token, Integer rows, Integer page, String agentId, String status) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			Map<String,Object> code=codeService.receive(page,rows,agentId,status);
			if (code.size() > 0) {
				// RedisUtils.returnResource(jedis);
				return YJResult.ok(code);
			}
			// RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.NONE_RECORD, "无此代理商的激活码记录");
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "generatedCode")
	public @ResponseBody
	YJResult addCode(String token, String agentId) {
		return null;
//		// Jedis jedis=RedisUtils.getJedis();
//		if(redisUtils.exists(token)){
//			return codeService.find(jedis,agentId);
//			/*if(agentId.startsWith("T")){
//				Agent n = new Agent();
//				n.setMerId(agentId);
//				Agent h=agentService.findByObject(n);
//				int generatedCode=Integer.parseInt(h.getGeneratedCode());
//				int notused=Integer.parseInt(h.getNotused());
//				h.setGeneratedCode(generatedCode+1+"");
//				h.setNotused(notused+1+"");
//				Long k=System.currentTimeMillis();
//				String s=agentId+k;
//				String r=MD5Util.getMD5String(s);
//				Code v=new Code(r, "N", System.currentTimeMillis()+"", agentId);
//				codeService.save(v);
//				agentService.update(h);
//				// RedisUtils.returnResource(jedis);
//				return YJResult.ok(v);
//			}
//			Agent n = new Agent();
//			n.setMerId(agentId);
//			Agent h=agentService.findByObject(n);
//			int generatedCode=Integer.parseInt(h.getGeneratedCode());
//			int notused=Integer.parseInt(h.getNotused());
//			int assign=Integer.parseInt(h.getAssign());
//			if(assign > 0){
//				h.setGeneratedCode(generatedCode+1+"");
//				h.setNotused(notused+1+"");
//				h.setAssign(assign-1+"");
//				Long k=System.currentTimeMillis();
//				String s=agentId+k;
//				String r=MD5Util.getMD5String(s);
//				Code v=new Code(r, "N", System.currentTimeMillis()+"", agentId);
//				codeService.save(v);
//				agentService.update(h);
//				// RedisUtils.returnResource(jedis);
//				return YJResult.ok(v);
//			}*/
//		}
//		// RedisUtils.returnResource(jedis);
//		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "activation")
	public @ResponseBody
	YJResult activation(String token, String merChantId, String activationCode) {
//		// Jedis jedis=RedisUtils.getJedis();
//		if(redisUtils.exists(token)){
//			return codeService.get(jedis,merChantId,activationCode);
//		}
//		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "selectCodes")
	public @ResponseBody
	YJResult selectCodes(String token, String orderNo) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			Code code = new Code();
			code.setOrderNo(orderNo);
			return YJResult.ok(codeService.findByObject(code));
		}
		// RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "generatedCodes")
	public @ResponseBody
	YJResult adds(String agentId) {
		return codeService.finds(agentId);
	}

}
