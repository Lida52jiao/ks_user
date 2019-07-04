package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.MerCode;
import com.yjkj.ks_user.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface MerCodeMapper extends MyMapper<MerCode> {
	
	void getLock(@Param("agentId") String agentId);

	MerCode get(MerCode merCode);

	void alter(@Param("agentId") String agentId, @Param("generatedCode") int generatedCode, @Param("notused") int notused, @Param("assign") int assign);

	void revises(@Param("agentId") String agentId, @Param("used") int used, @Param("notused") int notused);
	
}
