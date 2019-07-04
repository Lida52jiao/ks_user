package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.entity.MerChantsRate;

import java.util.List;

public interface MerChantsRateService extends BaseService<MerChantsRate> {

	void find(String merChantId, String appId, String institutionId, List<MerChantsRate> list);

	void alter(MerChants h, MerChantsRate merChantsRates);

	void send(MerChants h);


}
