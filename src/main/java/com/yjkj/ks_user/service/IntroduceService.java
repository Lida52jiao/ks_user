package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.Introduce;

import java.util.Map;

public interface IntroduceService extends BaseService<Introduce> {

	Map<String, Object> receive(Integer page, Integer rows);

}
