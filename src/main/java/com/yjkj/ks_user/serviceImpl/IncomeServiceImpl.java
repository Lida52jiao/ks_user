package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Income;
import com.yjkj.ks_user.mapper.IncomeMapper;
import com.yjkj.ks_user.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl extends BaseServiceImpl<Income> implements IncomeService {
	
	@Autowired
	private IncomeMapper incomeMapper;

	public List<Number> gain() {
		List<Number> n = incomeMapper.get();
		return n;
	}

}
