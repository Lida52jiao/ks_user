package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.CardInformation;
import com.yjkj.ks_user.util.MyMapper;

public interface CardInformationMapper extends MyMapper<CardInformation> {
	
	int get(String merChantId);

	int getcard(String merChantId);

}
