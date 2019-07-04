package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.News;

import java.util.Map;

public interface NewsService extends BaseService<News> {

	Map<String, Object> receive(Integer page, Integer rows, String merChantId, String appId);

}
