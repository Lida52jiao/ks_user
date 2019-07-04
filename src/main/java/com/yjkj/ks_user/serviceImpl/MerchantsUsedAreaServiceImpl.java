package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.KSMerUsedArea;
import com.yjkj.ks_user.mapper.MerchantsUsedAreaMapper;
import com.yjkj.ks_user.service.MerchantsUsedAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantsUsedAreaServiceImpl extends BaseServiceImpl<KSMerUsedArea> implements
MerchantsUsedAreaService  {
	
	@Autowired
	private MerchantsUsedAreaMapper merchantsUsedAreaMapper;

}
