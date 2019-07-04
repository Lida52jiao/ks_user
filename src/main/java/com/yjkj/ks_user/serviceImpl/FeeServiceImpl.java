package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Fee;
import com.yjkj.ks_user.mapper.FeeMapper;
import com.yjkj.ks_user.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl extends BaseServiceImpl<Fee> implements FeeService {
	
	@Autowired
	private FeeMapper feeMapper;

}
