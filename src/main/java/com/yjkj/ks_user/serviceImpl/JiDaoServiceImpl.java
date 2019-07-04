package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.JiDao;
import com.yjkj.ks_user.mapper.JiDaoMapper;
import com.yjkj.ks_user.service.JiDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiDaoServiceImpl extends BaseServiceImpl<JiDao> implements JiDaoService {
	
	@Autowired
	private JiDaoMapper jiDaoMapper;

}
