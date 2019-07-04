package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.Income;
import com.yjkj.ks_user.util.MyMapper;

import java.util.List;

public interface IncomeMapper extends MyMapper<Income> {

	List<Number> get();

}
