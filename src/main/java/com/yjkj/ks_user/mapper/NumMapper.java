package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.Num;
import com.yjkj.ks_user.entity.Number;
import com.yjkj.ks_user.util.MyMapper;

import java.util.List;

public interface NumMapper extends MyMapper<Num> {

	List<Number> get(Num n);

}
