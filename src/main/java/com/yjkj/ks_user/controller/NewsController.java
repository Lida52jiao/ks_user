package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.News;
import com.yjkj.ks_user.service.NewsService;
import com.yjkj.ks_user.util.YJ;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("News")
public class NewsController extends BaseController {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = "selectNewsList")
	public @ResponseBody
	YJResult get(Integer rows, Integer page, String merChantId, String appId) {
		Map<String,Object> news=newsService.receive(page,rows,merChantId,appId);
		return YJResult.ok(news);	
	}
	
	@RequestMapping(value = "sends")
	public @ResponseBody
    String sends(String merChantId, String msg) {
		YJ.push(merChantId, msg);
		News news=new News(merChantId, msg, System.currentTimeMillis()+"");
		return newsService.save(news);
	}
}
