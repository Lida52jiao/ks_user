package com.yjkj.ks_user.service;

import com.github.pagehelper.PageInfo;
import com.yjkj.ks_user.entity.FixReward;

import java.util.List;

public interface KSFixedRewardService {
	
	List<FixReward> getAll();
	
	PageInfo<FixReward> getAllByPage();
	
	Integer insert(FixReward fix);
	
	FixReward getById(Long id);
	
	Integer update(FixReward fix);

}
