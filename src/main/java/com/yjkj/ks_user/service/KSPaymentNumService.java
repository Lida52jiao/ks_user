package com.yjkj.ks_user.service;

import com.github.pagehelper.PageInfo;
import com.yjkj.ks_user.entity.PaymentNum;

import java.util.List;

public interface KSPaymentNumService {
	
	List<PaymentNum> getAll(String paymentidentity);
	
	PageInfo<PaymentNum> getAllByPage();
	
	Integer insert(PaymentNum pay);
	
	PaymentNum getById(Long id);
	
	Integer update(PaymentNum pay);

}
