package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Areas;
import com.yjkj.ks_user.mapper.AreasMapper;
import com.yjkj.ks_user.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AreasServiceImpl extends BaseServiceImpl<Areas> implements AreasService {
	
	@Autowired
	private AreasMapper areasMapper;

}
