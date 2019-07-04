package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Profit;
import com.yjkj.ks_user.mapper.ProfitMapper;
import com.yjkj.ks_user.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfitServiceImpl extends BaseServiceImpl<Profit> implements ProfitService {
	
	@Autowired
	private ProfitMapper profitMapper;

}
