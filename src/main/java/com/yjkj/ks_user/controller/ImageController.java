package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.Image;
import com.yjkj.ks_user.service.ImageService;
import com.yjkj.ks_user.util.MD5Util;
import com.yjkj.ks_user.util.OSSClientUtil;
import com.yjkj.ks_user.util.RedisUtils;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Controller
@RequestMapping("Image")
public class ImageController extends BaseController {
	
	@Autowired
	private ImageService imageService;
	@Autowired
	private RedisUtils redisUtils ;
	
	@RequestMapping(value = "adduserIDCardA")
	public @ResponseBody
	YJResult adduserIDCardA(String token, String merChantId, String name,
                            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			String filePath = "";
			// 判断文件是否为空
			if (!file.isEmpty()) {
				try {
					// 文件保存路径
					filePath = request.getSession().getServletContext()
							.getRealPath("/")
							+ "images/" + merChantId +name+ file.getOriginalFilename();
					// 转存文件
					file.transferTo(new File(filePath));
				} catch (Exception e) {
					e.printStackTrace();
					return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
				}
			}
			String s = MD5Util.getMD5String(filePath);
			String ossKey = "userIDCardA/" + s;
			String result = "";
			OSSClientUtil clientUtil = new OSSClientUtil();
			Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
					* 365 * 10);
			// 上传
			InputStream instream = null;
			try {
				instream = new FileInputStream(filePath);
				clientUtil.uploadFile2OSS(instream, ossKey);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
			}
			// 获取url
			URL url = clientUtil.createUrl(ossKey, expiration);
			clientUtil.destory();
			result = url.toString();
			Image n=new Image();
			n.setMerChantId(merChantId);
			Image h=imageService.findByObject(n);
			if(h != null){
				
				h.setUserIDCardA(result);
				imageService.update(h);
				return YJResult.ok(result);
			}
			Image v=new Image();
			v.setMerChantId(merChantId);
			v.setUserIDCardA(result);
			imageService.save(v);
			return YJResult.ok(result);
		}
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
		
	}

	@RequestMapping(value = "adduserIDCardB")
	public @ResponseBody
	YJResult adduserIDCardB(String token, String merChantId, String name,
                            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			String filePath = "";
			// 判断文件是否为空
			if (!file.isEmpty()) {
				try {
					// 文件保存路径
					filePath = request.getSession().getServletContext()
							.getRealPath("/")
							+ "images/" + merChantId +name+ file.getOriginalFilename();
					// 转存文件
					file.transferTo(new File(filePath));
				} catch (Exception e) {
					e.printStackTrace();
					return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
				}
			}
			String s = MD5Util.getMD5String(filePath);
			String ossKey = "userIDCardB/" + s;
			String result = "";
			OSSClientUtil clientUtil = new OSSClientUtil();
			Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
					* 365 * 10);
			// 上传
			InputStream instream = null;
			try {
				instream = new FileInputStream(filePath);
				clientUtil.uploadFile2OSS(instream, ossKey);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
			}
			// 获取url
			URL url = clientUtil.createUrl(ossKey, expiration);
			clientUtil.destory();
			result = url.toString();
			Image n=new Image();
			n.setMerChantId(merChantId);
			Image h=imageService.findByObject(n);
			if(h != null){
				h.setUserIDCardB(result);
				imageService.update(h);
				return YJResult.ok(result);
			}
			Image v=new Image();
			v.setMerChantId(merChantId);
			v.setUserIDCardB(result);
			imageService.save(v);
			return YJResult.ok(result);
		}
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
		
	}

	@RequestMapping(value = "addcardImgA")
	public @ResponseBody
	YJResult cardImgA(String token, String merChantId, String name,
                      @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		// Jedis jedis=RedisUtils.getJedis();
		if(redisUtils.exists(token)){
			String filePath = "";
			// 判断文件是否为空
			if (!file.isEmpty()) {
				try {
					// 文件保存路径
					filePath = request.getSession().getServletContext()
							.getRealPath("/")
							+ "images/" + merChantId +name+ file.getOriginalFilename();
					// 转存文件
					file.transferTo(new File(filePath));
				} catch (Exception e) {
					e.printStackTrace();
					return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
				}
			}
			String s = MD5Util.getMD5String(filePath);
			String ossKey = "cardImgA/" + s;
			String result = "";
			OSSClientUtil clientUtil = new OSSClientUtil();
			Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
					* 365 * 10);
			// 上传
			InputStream instream = null;
			try {
				instream = new FileInputStream(filePath);
				clientUtil.uploadFile2OSS(instream, ossKey);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
			}
			// 获取url
			URL url = clientUtil.createUrl(ossKey, expiration);
			clientUtil.destory();
			result = url.toString();
			Image n=new Image();
			n.setMerChantId(merChantId);
			Image h=imageService.findByObject(n);
			if(h != null){
				h.setCardImgA(result);
				imageService.update(h);
				return YJResult.ok(result);
			}
			Image v=new Image();
			v.setMerChantId(merChantId);
			v.setCardImgA(result);
			imageService.save(v);
			return YJResult.ok(result);
		}
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
		
}
