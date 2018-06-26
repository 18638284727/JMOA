package com.jm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.DepartmentDao;
import com.jm.entity.Department;
import com.jm.service.DepartmentService;

/**
 * 部门业务逻辑接口实现
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	
	@Transactional(readOnly = true)
	@Override
	public Department getDepartmentByDid(Integer dId) {
		return departmentDao.getDepartmentByDid(dId);
	}

	@Override
	public List<Department> getDepartmentByCid(Integer cId) {
		return departmentDao.getDepartmentByCid(cId);
	}

}
