package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Credit;
import com.yjkj.ks_user.mapper.CreditMapper;
import com.yjkj.ks_user.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl extends BaseServiceImpl<Credit> implements CreditService {
	
	@Autowired
	private CreditMapper creditMapper;

}
