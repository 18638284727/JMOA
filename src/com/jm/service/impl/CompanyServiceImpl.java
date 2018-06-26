package com.jm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.CompanyDao;
import com.jm.entity.Company;
import com.jm.service.CompanyService;

/**
 * 公司业务逻辑接口实现，处理逻辑
 */
@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyDao companyDao;
	
	@Transactional(readOnly = true)
	@Override
	public Company getCompanyById(Integer cId) {
		return companyDao.getCompanyById(cId);
	}

	@Override
	public List<Company> getAllCompany() {
		return companyDao.getAllCompany();
	}

}
