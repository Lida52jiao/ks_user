package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Subsidy;
import com.yjkj.ks_user.mapper.SubsidyMapper;
import com.yjkj.ks_user.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubsidyServiceImpl extends BaseServiceImpl<Subsidy> implements SubsidyService {
	
	@Autowired
	private SubsidyMapper subsidyMapper; 

}
