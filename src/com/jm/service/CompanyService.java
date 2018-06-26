package com.jm.service;

import java.util.List;

import com.jm.entity.Company;

/**
 * 公司业务逻辑接口
 */
public interface CompanyService {

	/**
	 * 根据编号查询公司
	 * @param cId 公司编号
	 * @return 公司记录
	 */ 
	public Company getCompanyById(Integer cId);
	
	/**
	 * 获取所有的公司
	 * @return
	 */
	public List<Company> getAllCompany();
	
}
