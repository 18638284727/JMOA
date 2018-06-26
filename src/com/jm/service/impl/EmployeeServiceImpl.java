package com.jm.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.EmployeeDao;
import com.jm.entity.Employee;
import com.jm.service.EmployeeService;
import com.jm.utils.CipherEncryption;

/**
 * 职员接口实现类，处理逻辑
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao; // 注入dao层
	
	@Transactional(readOnly = true)
	@Override
	public Integer checkUsername(String username) {
		return employeeDao.checkUsername(username);
	}

	@RequiresPermissions(value = {"超级管理员:entry", "管理员:entry"}, logical = Logical.OR)
	@Transactional
	@Override
	public Integer entry(Employee employee){
		String password = employee.getPassword();
		String username = employee.getUsername();
		Object md5 = CipherEncryption.MD5(password, username);
		employee.setPassword(md5.toString());
		return employeeDao.entry(employee);
	}

	@Transactional(readOnly = true)
	@Override
	public Employee getEmployeeByEid(Integer eId) {
		return employeeDao.getEmployeeByEid(eId);
	}

	@Override
	public List<Employee> getEmployeeByDid(Integer dId) {
		return employeeDao.getEmployeeByDid(dId);
	}
	
}
