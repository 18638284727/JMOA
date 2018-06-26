package com.jm.service;

import java.util.List;

import com.jm.entity.Employee;

/**
 * 职员接口，处理逻辑
 */
public interface EmployeeService {

	/**
	 * 验证用户名
	 * @param username 用户名
	 * @return 条数
	 */
	public Integer checkUsername(String username);
	
	/**
	 * 入职员工信息的录入及分配给该职员账号密码
	 * @param employee 入职职员信息
	 * @return 职员账号密码
	 */
	public Integer entry(Employee employee);
	
	/**
	 * 根据职员编号返回职员记录
	 * @param eId 职员编号
	 * @return 职员记录
	 */
	public Employee getEmployeeByEid(Integer eId);
	
	/**
	 * 根据部门编号返回该部门所有职员记录
	 * @param eId 职员编号
	 * @return 职员记录
	 */
	public List<Employee> getEmployeeByDid(Integer dId);
	
}
