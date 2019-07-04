package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.Code;
import com.yjkj.ks_user.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface CodeMapper extends MyMapper<Code> {

	List<Code> get(Map<String, Object> m);

	int selectCode(Map<String, Object> m);

}
