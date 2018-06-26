package com.jm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.NationDao;
import com.jm.entity.Nation;
import com.jm.service.NationService;

/**
 * 民族业务逻辑接口实现
 */
@Service
public class NationServiceImpl implements NationService{

	@Autowired
	private NationDao nationDao;
	
	@Transactional(readOnly = true)
	@Override
	public Nation getNationById(Integer nId) {
		return nationDao.getNationById(nId);
	}

}
