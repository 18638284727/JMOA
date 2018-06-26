package com.jm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.StateDao;
import com.jm.entity.State;
import com.jm.service.StateService;

/**
 * 状态业务逻辑接口实现
 */
@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateDao stateDao;
	
	@Transactional(readOnly = true)
	@Override
	public State getStateById(Integer sId) {
		return stateDao.getStateById(sId);
	}

}
