package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.AppName;
import com.yjkj.ks_user.mapper.AppNameMapper;
import com.yjkj.ks_user.service.AppNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppNameServiceImpl extends BaseServiceImpl<AppName> implements AppNameService {
	
	@Autowired
	private AppNameMapper appNameMapper;

}
