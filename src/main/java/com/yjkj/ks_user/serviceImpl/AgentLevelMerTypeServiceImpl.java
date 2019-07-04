package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.AgentLevelMerType;
import com.yjkj.ks_user.mapper.AgentLevelMerTypeMapper;
import com.yjkj.ks_user.service.AgentLevelMerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentLevelMerTypeServiceImpl extends BaseServiceImpl<AgentLevelMerType> implements AgentLevelMerTypeService {

	@Autowired
	private AgentLevelMerTypeMapper agentLevelMerTypeMapper;
}
