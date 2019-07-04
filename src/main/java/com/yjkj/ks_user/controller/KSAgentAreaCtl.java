package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Agentarea;
import com.yjkj.ks_user.service.AgentareaService;
import com.yjkj.ks_user.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 根据代理商号
 * 查询该代理商是
 * 哪个级别的代理
 * @author N
 *
 */
@Controller
@RequestMapping("/KSgetAgentArea/")
public class KSAgentAreaCtl extends BaseController {
	
	@Autowired
	private AgentareaService agentareaService;
	
	@RequestMapping("areaName")
	@ResponseBody
	public YJResult getAllMessage(String merChantId){
		Agentarea entity = new Agentarea();
		entity.setMerchantId(merChantId);
		List<Agentarea> agentArea = agentareaService.queryObjectForList(entity);
		if(agentArea.size()!=0){
			return new YJResult("0000", "数据返回成功", agentArea.get(0).getAreaName());
		}
		return new YJResult("0000", "该商户未绑定过区域代理", "");
	}
}
