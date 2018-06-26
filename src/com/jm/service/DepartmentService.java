package com.jm.service;

import java.util.List;

import com.jm.entity.Department;

/**
 * 部门业务逻辑接口
 */
public interface DepartmentService {

	/**
	 * 根据部门编号查询部门记录
	 * @param dId 部门编号
	 * @return 部门记录
	 */
	public Department getDepartmentByDid(Integer dId);
	
	/**
	 * 根据公司编号查询该公司所有部门记录
	 * @param cId 部门编号
	 * @return 部门记录
	 */
	public List<Department> getDepartmentByCid(Integer cId);
	
}
