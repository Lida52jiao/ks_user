package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.MerChants;

public interface OpenAccountService {
	
	void openAccount(MerChants newMer, String merChantId, String oneMerId);

}
