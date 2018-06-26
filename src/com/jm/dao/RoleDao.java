package com.jm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 角色接口
 */
public interface RoleDao {

	/**
	 * 拥有指定权限编号的所有角色集合
	 * @param jId 权限编号
	 * @return 角色集合
	 */
	public List<String> getRoleByJid(@Param("jId") Integer jId);
	
}
