package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Area;
import com.yjkj.ks_user.mapper.AreaMapper;
import com.yjkj.ks_user.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;

}
