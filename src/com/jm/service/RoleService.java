package com.jm.service;

import java.util.List;

/**
 * 角色接口，处理逻辑
 */
public interface RoleService {

	/**
	 * 拥有指定权限编号的所有角色集合
	 * @param jId 权限编号
	 * @return 角色集合
	 */
	public List<String> getRoleByJid(Integer jId);
	
}
