package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.UserEntity;
import com.yjkj.ks_user.mapper.UserMapper;
import com.yjkj.ks_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements
	UserService {

	@Autowired
	private UserMapper userMapper;
}
