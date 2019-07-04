package com.yjkj.ks_user.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.yjkj.ks_user.entity.FixReward;
import com.yjkj.ks_user.entity.FixRewardExample;
import com.yjkj.ks_user.mapper.FixRewardMapper;
import com.yjkj.ks_user.service.KSFixedRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KSFixedRewardServiceImpl implements KSFixedRewardService {

	@Autowired
	private FixRewardMapper FixRewardMapper;

	public List<FixReward> getAll() {
		FixRewardExample example = new FixRewardExample();
		List<FixReward> list = FixRewardMapper.selectByExample(example);
		return list;
	}

	//以下方法后台用的
	public PageInfo<FixReward> getAllByPage() {
		FixRewardExample example = new FixRewardExample();
		List<FixReward> list = FixRewardMapper.selectByExample(example);
		return new PageInfo<FixReward>(list);
	}

	public Integer insert(FixReward fix) {
		Integer count = FixRewardMapper.insert(fix);
		return count;
	}

	public FixReward getById(Long id) {
		FixReward fix = FixRewardMapper.selectByPrimaryKey(id);
		return fix;
	}

	public Integer update(FixReward fix) {
		Integer count = FixRewardMapper.updateByPrimaryKey(fix);
		return count;
	}

}
