package com.yjkj.ks_user.service;


import com.yjkj.ks_user.entity.IndexPush;

import java.util.List;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 11:01
 */
public interface IndexPushService extends BaseService<IndexPush> {

    List<IndexPush> findNewPush(IndexPush ip);
}
