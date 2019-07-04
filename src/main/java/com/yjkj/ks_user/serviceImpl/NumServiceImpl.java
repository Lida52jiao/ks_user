package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Num;
import com.yjkj.ks_user.entity.Number;
import com.yjkj.ks_user.mapper.NumMapper;
import com.yjkj.ks_user.service.NumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumServiceImpl extends BaseServiceImpl<Num> implements NumService {
	
	@Autowired
	private NumMapper numMapper;

	public List<Number> gain(Num n) {
		List<Number> t = numMapper.get(n);
		return t;
	} 

}
