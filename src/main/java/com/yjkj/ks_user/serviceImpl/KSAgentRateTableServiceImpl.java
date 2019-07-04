package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.KSAgentRateTable;
import com.yjkj.ks_user.entity.KSAgentRateTableExample;
import com.yjkj.ks_user.mapper.KSAgentRateTableMapper;
import com.yjkj.ks_user.service.KSAgentRateTableSAervice;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KSAgentRateTableServiceImpl implements KSAgentRateTableSAervice {
	
	@Autowired
	private KSAgentRateTableMapper ksAgentRateTableMapper;

	public List<KSAgentRateTable> getAgentRateByLevel(String agentLevel) {
		KSAgentRateTableExample example = new KSAgentRateTableExample();
		KSAgentRateTableExample.Criteria cri = new KSAgentRateTableExample().createCriteria();
		if(!StringUtils.isBlank(agentLevel)){
			cri.andAgentLevelEqualTo(agentLevel);
		}
		example.or(cri);
		List<KSAgentRateTable> list = ksAgentRateTableMapper.selectByExample(example);
		return list;
	}

}
