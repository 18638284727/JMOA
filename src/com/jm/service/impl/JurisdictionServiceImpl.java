package com.jm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.JurisdictionDao;
import com.jm.entity.Jurisdiction;
import com.jm.service.JurisdictionService;
import com.jm.service.RoleService;

/**
 * 权限接口实现类，处理逻辑
 */
@Service
public class JurisdictionServiceImpl implements JurisdictionService{

	@Autowired
	private JurisdictionDao jurisdictionDao;
	
	@Autowired
	private RoleService roleService;
	
	@Transactional(readOnly = true)
	@Override
	public List<Jurisdiction> getAll() {
		return jurisdictionDao.getAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Jurisdiction> getParentNode() {
		List<Jurisdiction> parentNode = jurisdictionDao.getParentNode();
		for (Jurisdiction jurisdiction : parentNode) {
			Integer id = jurisdiction.getjId();
			List<Jurisdiction> childrenNode = getChildrenNode(id);
			jurisdiction.setChildren(childrenNode);
			List<String> roles = roleService.getRoleByJid(id);
			String strs = "";
			if(roles != null && roles.size() > 0)
			{
				for (String string : roles) {
					strs += string + ",";
				}
			}
			jurisdiction.setRoles(strs);
		}
		return parentNode;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Jurisdiction> getChildrenNode(Integer jParent) {
		return jurisdictionDao.getChildrenNode(jParent);
	}

}
