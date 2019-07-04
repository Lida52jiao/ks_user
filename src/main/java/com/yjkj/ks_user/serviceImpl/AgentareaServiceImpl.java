package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Agentarea;
import com.yjkj.ks_user.mapper.AgentareaMapper;
import com.yjkj.ks_user.service.AgentareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentareaServiceImpl extends BaseServiceImpl<Agentarea> implements AgentareaService {
	
	@Autowired
	private AgentareaMapper agentareaMapper; 

}
