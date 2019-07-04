package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.Num;
import com.yjkj.ks_user.entity.Number;

import java.util.List;

public interface NumService extends BaseService<Num> {

	List<Number> gain(Num n);

}
