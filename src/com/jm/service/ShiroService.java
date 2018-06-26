package com.jm.service;

import java.util.List;

import com.jm.entity.Employee;
import com.jm.entity.Role;

/**
 * 处理shiro问题，创建shiro接口，用来处理代理问题
 */
public interface ShiroService {

	/**
	 * 验证登录
	 * @param username 用户名
	 * @return 职员实体
	 */
	public Employee login(String username);
	
	/**
	 * 根据职员编号获取该职员的所有角色
	 * @param eId 职员编号
	 * @return 该职员的角色集合
	 */
	public List<Role> getRoleByEid(Integer eId);
	
	/**
	 * 获取角色所拥有的操作
	 * @param rId 角色编号
	 * @return 角色拥有的操作集合
	 */
	public List<String> getOperationByRid(Integer rId);
}
