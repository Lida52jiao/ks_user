package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Agentmerlevel;
import com.yjkj.ks_user.mapper.AgentmerlevelMapper;
import com.yjkj.ks_user.service.AgentmerlevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentmerlevelServiceImpl extends BaseServiceImpl<Agentmerlevel> implements AgentmerlevelService {
	
	@Autowired
	private AgentmerlevelMapper agentmerlevelMapper;

}
