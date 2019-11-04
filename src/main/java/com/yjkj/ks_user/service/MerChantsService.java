package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.DuanXin;
import com.yjkj.ks_user.entity.MerChant;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.entity.RenZhen;
import com.yjkj.ks_user.util.YJResult;

import java.math.BigDecimal;
import java.util.List;

public interface MerChantsService extends BaseService<MerChants> {

	int statistics(MerChants v);

	int receive(String merChantId);

	int find(String merChantId);

	DuanXin getduanXin(String institutionId, String appId);

	RenZhen getrenZhen(String institutionId, String appId);

	MerChant gain(MerChant t);

	YJResult get(String merChantId, String agentId, BigDecimal amount,
                 String orderNo);

	void send(MerChants h);

	void tran(String merName, String institutionId, String appId, String phone, String type);

	List<MerChants> getGainList(MerChants mer);

	void sends(MerChants h);

	void bind(MerChants k);

    Integer countByMobile(String mobile);
}
