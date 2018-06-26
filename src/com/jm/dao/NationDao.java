package com.jm.dao;

import org.apache.ibatis.annotations.Param;

import com.jm.entity.Nation;

/**
 * 民族接口
 */
public interface NationDao {

	/**
	 * 根据民族编号查询 
	 * @param nId 民族编号
	 * @return 民族记录
	 */
	public Nation getNationById(@Param("nId") Integer nId);
	
}
