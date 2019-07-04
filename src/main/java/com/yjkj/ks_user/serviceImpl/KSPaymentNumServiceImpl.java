package com.yjkj.ks_user.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.yjkj.ks_user.entity.PaymentNum;
import com.yjkj.ks_user.entity.PaymentNumExample;
import com.yjkj.ks_user.mapper.PaymentNumMapper;
import com.yjkj.ks_user.service.KSPaymentNumService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KSPaymentNumServiceImpl implements KSPaymentNumService {

	@Autowired
	private PaymentNumMapper paymentNumMapper;

	public List<PaymentNum> getAll(String paymentidentity) {
		PaymentNumExample example = new PaymentNumExample();
		PaymentNumExample.Criteria cri = new PaymentNumExample().createCriteria();
		if(!StringUtils.isBlank(paymentidentity)){
			cri.andPaymentIdentityEqualTo(paymentidentity);
		}
		example.or(cri);
		List<PaymentNum> list = paymentNumMapper.selectByExample(example);
		return list;
	}

	public PageInfo<PaymentNum> getAllByPage() {
		PaymentNumExample example = new PaymentNumExample();
		List<PaymentNum> list = paymentNumMapper.selectByExample(example);
		return new PageInfo<PaymentNum>(list);
	}

	public Integer insert(PaymentNum pay) {
		Integer count = paymentNumMapper.insert(pay);
		return count;
	}

	public PaymentNum getById(Long id) {
		PaymentNum num = paymentNumMapper.selectByPrimaryKey(id);
		return num;
	}

	public Integer update(PaymentNum pay) {
		Integer count = paymentNumMapper.updateByPrimaryKey(pay);
		return count;
	}
}
