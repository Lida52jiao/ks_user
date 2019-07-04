package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.FixReward;
import com.yjkj.ks_user.service.KSFixedRewardService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 卡神的固定分配钱数
 * @author N
 *
 */
@Controller
@RequestMapping("/KSgetFixReward/")
public class KsFixedRewardCtl {
	
	@Autowired
	private KSFixedRewardService ksFixedRewardService;
	
	@RequestMapping("reward")
	@ResponseBody
	public YJResult getAllMessage(){
		List<FixReward> list = ksFixedRewardService.getAll();
		return new YJResult("0000", "数据返回成功", list.get(0));
	}

}
