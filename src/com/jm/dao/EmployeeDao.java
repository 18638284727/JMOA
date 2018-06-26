package com.jm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jm.entity.Employee;

/**
 * 职员接口
 */
public interface EmployeeDao {

	/**
	 * 验证过用户名
	 * @param username 用户名
	 * @return 条数
	 */
	public Integer checkUsername(@Param("username") String username);
	
	/**
	 * 入职添加信息
	 * @param employee 职员信息
	 * @return 受影响的行数
	 */
	public Integer entry(Employee employee);
	
	/**
	 * 根据职员编号返回职员记录
	 * @param eId 职员编号
	 * @return 职员记录
	 */
	public Employee getEmployeeByEid(@Param("eId") Integer eId);
	
	/**
	 * 根据部门编号返回该部门所有职员记录
	 * @param eId 职员编号
	 * @return 职员记录
	 */
	public List<Employee> getEmployeeByDid(@Param("dId") Integer dId);
	
}
