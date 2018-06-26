package com.jm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.RoleDao;
import com.jm.service.RoleService;

/**
 * 角色接口实现类，实现业务逻辑
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<String> getRoleByJid(Integer jId) {
		return roleDao.getRoleByJid(jId);
	}

}
