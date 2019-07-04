package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.SureRecord;
import com.yjkj.ks_user.mapper.SureRecordMapper;
import com.yjkj.ks_user.service.SureRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SureRecordServiceImpl implements SureRecordService {
	
	@Autowired
	private SureRecordMapper sureRecordMapper;


//	public Integer insertSureRecord(SureRecord record) {
//			Integer count = sureRecordMapper.insert(record);
//			return count;
//	}

	public Integer insertSureRecord(SureRecord record) {
		Integer count = sureRecordMapper.insert(record);
		return count;
	}

}
