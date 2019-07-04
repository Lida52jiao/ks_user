package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Reward;
import com.yjkj.ks_user.mapper.RewardMapper;
import com.yjkj.ks_user.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl extends BaseServiceImpl<Reward> implements RewardService {
	
	@Autowired
	private RewardMapper rewardMapper;

	public Reward find(Reward reward) {
		return rewardMapper.get(reward);
	} 

}
