package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Used;
import com.yjkj.ks_user.mapper.UsedMapper;
import com.yjkj.ks_user.service.UsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsedServiceImpl extends BaseServiceImpl<Used> implements UsedService {
	
	@Autowired
	private UsedMapper usedMapper;

}
