package com.yjkj.ks_user.mapper;


import com.yjkj.ks_user.entity.IndexPush;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 11:04
 */
public interface IndexPushMapper extends Mapper<IndexPush> {

    List<IndexPush> gain(IndexPush m);
}
