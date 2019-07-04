package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.mapper.MerChantsMapper;
import com.yjkj.ks_user.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KSUpAgentLevelServiceImpl implements KSUpAgentLevelService{
	private static final Log logger = LogFactory.getLog(KSUpAgentLevelServiceImpl.class);
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private MerChantsMapper merChantsMapper;
	@Autowired
	private AgentService agentService;
	@Autowired
	private UserService userService;
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private KSAgentRateTableSAervice ksAgentRateTableSAervice;
	@Autowired
	private AgentRateService agentRateService;
	@Autowired
	private RatesService ratesService;
	@Autowired
	private OpenAccountService openAccountService;
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public String upAgentLevel(MerChants newMer, String merChantId, String oneMerId) {
		newMer.setAgentStatus("1");
		newMer.setMerType("3");
		merChantsService.update(newMer);
		openAccountService.openAccount(newMer,merChantId,oneMerId);
		return "success";
	}
}
