package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Banner;
import com.yjkj.ks_user.mapper.BannerMapper;
import com.yjkj.ks_user.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner> implements BannerService {
	
	@Autowired
	private BannerMapper bannerMapper;

	public List<Banner> queryForList(Banner banner) {
		List<Banner> bannerList=bannerMapper.query(banner);
		return bannerList;
	}

}
