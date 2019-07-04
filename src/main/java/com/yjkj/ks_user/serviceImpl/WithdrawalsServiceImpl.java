package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Withdrawals;
import com.yjkj.ks_user.mapper.WithdrawalsMapper;
import com.yjkj.ks_user.service.WithdrawalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawalsServiceImpl extends BaseServiceImpl<Withdrawals> implements WithdrawalsService {
	
	@Autowired
	private WithdrawalsMapper withdrawalsMapper;

}
