package com.jm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jm.entity.Jurisdiction;

/**
 * 权限接口
 */
public interface JurisdictionDao {

	/**
	 * 获取所有的权限
	 * @return 权限集合
	 */
	public List<Jurisdiction> getAll();
	
	/**
	 * 获取所有的一级导航
	 * @return 一级导航集合
	 */
	public List<Jurisdiction> getParentNode();
	
	/**
	 * 根据一级导航的编号查询该导航的左右子节点
	 * @param jParent 一级导航编号
	 * @return 子节点集合
	 */
	public List<Jurisdiction> getChildrenNode(@Param("jParent") Integer jParent);
}
