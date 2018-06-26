package com.jm.dao;

import org.apache.ibatis.annotations.Param;

import com.jm.entity.State;

/**
 * 账号状态接口
 */
public interface StateDao {

	/**
	 * 根据编号查询状态
	 * @param sId 状态编号
	 * @return 状态记录
	 */
	public State getStateById(@Param("sId") Integer sId);
	
}
