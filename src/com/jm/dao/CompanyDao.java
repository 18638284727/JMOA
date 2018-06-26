package com.jm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jm.entity.Company;

/**
 * 公司接口
 */
public interface CompanyDao {

	/**
	 * 根据编号查询公司
	 * @param cId 公司编号
	 * @return 公司记录
	 */ 
	public Company getCompanyById(@Param("cId") Integer cId);
	
	/**
	 * 获取所有的公司
	 * @return
	 */
	public List<Company> getAllCompany();
	
}
