package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Type;
import com.yjkj.ks_user.mapper.TypeMapper;
import com.yjkj.ks_user.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TypeServiceImpl extends BaseServiceImpl<Type> implements TypeService {
	
	@Autowired
	private TypeMapper typeMapper;

}
