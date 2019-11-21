package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.MerChant;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.util.MyMapper;

import java.util.List;

public interface MerChantsMapper extends MyMapper<MerChants> {

	int gain(MerChants v);

	int selectCode(String merChantId);

	int get(String merChantId);

	MerChant receive(MerChant t);

	List<MerChants> gainList(MerChants m);

	/**
	  * // TODO 通过手机号统计用户注册
	  * @author wdz
	  * @param
	  * @date 2019/11/4 13:49
	  * @return
	  */
    Integer countByMobile(String mobile);


}
