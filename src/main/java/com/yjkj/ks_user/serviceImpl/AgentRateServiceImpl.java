package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.AgentRate;
import com.yjkj.ks_user.mapper.AgentRateMapper;
import com.yjkj.ks_user.service.AgentRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentRateServiceImpl extends BaseServiceImpl<AgentRate> implements AgentRateService {
	
	@Autowired
	private AgentRateMapper agentRateMapper;

}
