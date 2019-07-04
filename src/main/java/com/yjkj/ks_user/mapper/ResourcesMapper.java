package com.yjkj.ks_user.mapper;

import com.yjkj.ks_user.entity.GroupEntity;
import com.yjkj.ks_user.entity.ResourcesEntity;
import com.yjkj.ks_user.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface ResourcesMapper extends MyMapper<ResourcesEntity> {

	public List<ResourcesEntity> findResourcesByUserId(Long userId);

	public List<ResourcesEntity> findResourcessByRoleId(String roleId);

	public List<ResourcesEntity> findResourcessByMap(
            Map<String, String> queryMap);

	public List<ResourcesEntity> findRes(Map<String, String> queryMap);

	public void deleteByRoleId(String roleId);

	public void deleteByUserId(String userId);

	public void deleteRoleByUserId(String userId);

	public void batchAddRoleGroup(List<GroupEntity> list);

	public void batchAddUserGroup(List<GroupEntity> list);

	public void treeNodes();
	
	public List<String> findRoleNameByUserId(String userId);

}
