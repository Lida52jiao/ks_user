package com.yjkj.ks_user.service;

import com.yjkj.ks_user.entity.Banner;

import java.util.List;

public interface BannerService extends BaseService<Banner> {

	List<Banner> queryForList(Banner banner);

}
