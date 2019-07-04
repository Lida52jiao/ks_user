package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Area;
import com.yjkj.ks_user.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 查询中国所有省市县
 * 并将省、市、县
 * 存为一个json字符串
 * ru 省1
 * 		2
 * 		3{
 * 			市1
 * 				2
 * 				3{
 * 					县1
 * 					2
 * 					3}
 * }
 * @author N
 *
 */
@Controller
@RequestMapping("/community/")
public class KSCountryCtl {
	
	@Autowired
	private AreaService areaService;
	
//	@RequestMapping(value="getCountrys")
//	@ResponseBody
//	public JSONObject getCountry(HttpServletResponse response){
//		response.addHeader("Access-Control-Allow-Origin", "*");// 跨域
//		JSONObject json = new JSONObject();
//		Area entityProvice = new Area();
//		entityProvice.setParent_id("0");
//		List<Area> proviceList = areaService.queryObjectForList(entityProvice);//查出所有省
//		for(Area provice:proviceList){
//			Area entityCity = new Area();
//			entityCity.setParent_id(provice.getId()+"");
//			List<Area> cityList = areaService.queryObjectForList(entityCity);//查出该省下面的所有市
//			for(Area city:cityList){
//				Area entityCountrys = new Area();
//				entityCountrys.setParent_id(city.getId()+"");
//				List<Area> countrysList = areaService.queryObjectForList(entityCountrys);//查出该市下面的所有县
//			}
//		}
//		json.put("data", "");
//		return json;
//	}
	@RequestMapping(value="getCountrys/{pId}")
	@ResponseBody
	public List<Area> getCountry(HttpServletResponse response, @PathVariable String pId){
		response.addHeader("Access-Control-Allow-Origin", "*");// 跨域
		Area entityProvice = new Area();
		entityProvice.setParent_id(pId);
		List<Area> proviceList = areaService.queryObjectForList(entityProvice);
		return proviceList;
	}
}
