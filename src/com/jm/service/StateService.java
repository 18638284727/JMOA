package com.jm.service;

import com.jm.entity.State;

/**
 * 状态业务逻辑接口
 */
public interface StateService {

	/**
	 * 根据编号查询状态
	 * @param sId 状态编号
	 * @return 状态记录
	 */
	public State getStateById(Integer sId);
	
}
