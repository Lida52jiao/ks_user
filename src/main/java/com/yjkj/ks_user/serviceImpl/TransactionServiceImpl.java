package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Transaction;
import com.yjkj.ks_user.mapper.TransactionMapper;
import com.yjkj.ks_user.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl extends BaseServiceImpl<Transaction>
		implements TransactionService {

	@Autowired
	private TransactionMapper transactionMapper;

}
