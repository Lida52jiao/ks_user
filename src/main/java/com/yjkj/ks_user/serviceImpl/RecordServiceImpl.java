package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Record;
import com.yjkj.ks_user.mapper.RecordMapper;
import com.yjkj.ks_user.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends BaseServiceImpl<Record> implements RecordService {
	
	@Autowired
	private RecordMapper recordMapper;  

}
