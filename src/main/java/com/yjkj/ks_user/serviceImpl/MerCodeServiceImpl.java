package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.MerCode;
import com.yjkj.ks_user.mapper.MerCodeMapper;
import com.yjkj.ks_user.service.MerCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerCodeServiceImpl extends BaseServiceImpl<MerCode> implements MerCodeService {
	
	@Autowired
	private MerCodeMapper merCodeMapper;

	public MerCode find(MerCode merCode) {
		return merCodeMapper.get(merCode);
	} 

}
