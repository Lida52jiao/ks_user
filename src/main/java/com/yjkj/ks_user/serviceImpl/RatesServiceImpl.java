package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Rates;
import com.yjkj.ks_user.mapper.RatesMapper;
import com.yjkj.ks_user.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatesServiceImpl extends BaseServiceImpl<Rates> implements RatesService {
	
	@Autowired
	private RatesMapper ratesMapper;

}
