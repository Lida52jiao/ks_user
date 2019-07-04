package com.yjkj.ks_user.serviceImpl;

import com.yjkj.ks_user.entity.Image;
import com.yjkj.ks_user.mapper.ImageMapper;
import com.yjkj.ks_user.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl extends BaseServiceImpl<Image> implements ImageService {
	
	@Autowired
	private ImageMapper imageMapper; 

}
