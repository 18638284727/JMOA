package com.jm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.ShiroDao;
import com.jm.entity.Employee;
import com.jm.entity.Role;
import com.jm.service.ShiroService;

/**
 * ShiroService 实现类，处理方法的逻辑
 */
@Service
public class ShiroServiceImpl implements ShiroService{

	@Autowired
	private ShiroDao shiroDao; // 注入dao层
	
	@Transactional(readOnly = true)
	@Override
	public Employee login(String username) {
		return shiroDao.login(username);
	}

	@Override
	public List<Role> getRoleByEid(Integer eId) {
		return shiroDao.getRoleByEid(eId);
	}

	@Override
	public List<String> getOperationByRid(Integer rId) {
		return shiroDao.getOperationByRid(rId);
	}

}
