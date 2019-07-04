package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.Banner;
import com.yjkj.ks_user.util.MyMapper;

import java.util.List;

public interface BannerMapper extends MyMapper<Banner> {

	List<Banner> query(Banner banner);

}
