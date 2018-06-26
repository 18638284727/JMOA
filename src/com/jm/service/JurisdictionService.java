package com.jm.service;

import java.util.List;

import com.jm.entity.Jurisdiction;

/**
 * 权限接口，处理逻辑
 */
public interface JurisdictionService {

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
	 * 根据一级导航的编号获取所有该一级导航的二级导航
	 * @param jParent 一级导航编号 
	 * @return 二级导航集合
	 */
	public List<Jurisdiction> getChildrenNode(Integer jParent);
}
