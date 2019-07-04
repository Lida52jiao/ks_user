package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.CardInformation;

public interface CardInformationService extends BaseService<CardInformation> {

	int find(String token, CardInformation cardInformation);

	int gains(String merChantId);

	int selectcard(String merChantId);

}
