package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.KSAgentRateTable;

import java.util.List;

public interface KSAgentRateTableSAervice {
	
	List<KSAgentRateTable> getAgentRateByLevel(String agentLevel);

}
