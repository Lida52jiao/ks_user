package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.IndexPush;
import com.yjkj.ks_user.mapper.IndexPushMapper;
import com.yjkj.ks_user.service.IndexPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 11:02
 */
@Service
public class IndexPushServiceImpl extends BaseServiceImpl<IndexPush> implements IndexPushService {

    @Autowired
    private IndexPushMapper indexPushMapper;
    @Override
    public List<IndexPush> findNewPush(IndexPush ip){

        return indexPushMapper.gain(ip);

    }

}
