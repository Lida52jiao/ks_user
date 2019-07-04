package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.News;
import com.yjkj.ks_user.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface NewsMapper extends MyMapper<News> {

	List<News> get(Map<String, Object> m);

	int selectCode(Map<String, Object> m);

}
