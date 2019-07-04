package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.ChannelRate;
import com.yjkj.ks_user.mapper.ChannelRateMapper;
import com.yjkj.ks_user.service.ChannelRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelRateServiceImpl extends BaseServiceImpl<ChannelRate> implements ChannelRateService {
	
	@Autowired
	private ChannelRateMapper channelRateMapper;

}
