package com.jm.service;

import com.jm.entity.Nation;

/**
 * 民族业务逻辑接口
 */
public interface NationService {

	/**
	 * 根据民族编号查询 
	 * @param nId 民族编号
	 * @return 民族记录
	 */
	public Nation getNationById(Integer nId);
	
}
