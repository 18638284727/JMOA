package com.jm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jm.entity.Employee;
import com.jm.entity.Role;

/**
 * Shiro不支持类代理，支持接口代理
 * Shiro事物的问题
 * 创建单独的ShiroDao用来处理Shiro的问题
 */
public interface ShiroDao {

	/**
	 * 登录验证方法
	 * @param username 用户名(唯一)
	 * @return 用户实体类
	 */
	public Employee login(@Param("username") String username);
	
	/**
	 * 根据职员编号获取该职员的所有角色
	 * @param eId 职员编号
	 * @return 该职员的角色集合
	 */
	public List<Role> getRoleByEid(@Param("eId")Integer eId);
	
	/**
	 * 获取角色所拥有的操作
	 * @param rId 角色编号
	 * @return 角色拥有的操作集合
	 */
	public List<String> getOperationByRid(@Param("rId") Integer rId);
	
}
