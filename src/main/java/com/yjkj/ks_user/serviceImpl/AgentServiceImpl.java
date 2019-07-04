package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Agent;
import com.yjkj.ks_user.mapper.AgentMapper;
import com.yjkj.ks_user.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl extends BaseServiceImpl<Agent> implements
		AgentService {

	@Autowired
	private AgentMapper agentMapper;

	public Agent gain(Agent a) {
		Agent agent = agentMapper.selectOne(a);
		return agent;
	}

}
