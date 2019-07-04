package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.Income;

import java.util.List;

public interface IncomeService extends BaseService<Income> {

	List<Number> gain();

}
