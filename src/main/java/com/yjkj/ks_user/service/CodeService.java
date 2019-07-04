package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.Code;
import com.yjkj.ks_user.util.YJResult;
import redis.clients.jedis.Jedis;


import java.util.Map;

public interface CodeService extends BaseService<Code> {

	Map<String, Object> receive(Integer page, Integer rows, String agentId,
                                String status);

	YJResult find(Jedis jedis, String agentId);

	YJResult get(Jedis jedis, String merChantId, String activationCode);

	YJResult finds(String agentId);

}
