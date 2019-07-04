package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.Introduce;
import com.yjkj.ks_user.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface IntroduceMapper extends MyMapper<Introduce> {

	List<Introduce> get(Map<String, Object> m);

	int selectCode(Map<String, Object> m);

}
