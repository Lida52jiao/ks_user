package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.MerCode;
import com.yjkj.ks_user.service.MerCodeService;
import com.yjkj.ks_user.util.RedisUtils;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("MerCode")
public class MerCodeController extends BaseController {
	
	@Autowired
	private MerCodeService merCodeService;
	@Autowired
	private RedisUtils redisUtils ;
	@RequestMapping(value = "selectCodes")
	public @ResponseBody
	YJResult selectCodes(String token, String agentId) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			MerCode merCode = new MerCode();
			merCode.setMerId(agentId);
			return YJResult.ok(merCodeService.findByPrimaryKey(merCode));
		}
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}

}
