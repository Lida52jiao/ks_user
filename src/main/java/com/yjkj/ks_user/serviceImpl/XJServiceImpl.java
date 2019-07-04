package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.XJ;
import com.yjkj.ks_user.mapper.XJMapper;
import com.yjkj.ks_user.service.XJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XJServiceImpl extends BaseServiceImpl<XJ> implements XJService {
	
	@Autowired
	private XJMapper xjMapper; 

}
