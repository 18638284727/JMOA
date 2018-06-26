package com.jm.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jm.dao.NoticeBulletinDao;
import com.jm.entity.Employee;
import com.jm.entity.NoticeBulletin;
import com.jm.service.NoticeBulletinService;

/**
 * 通知业务逻辑公告接口实现
 */
@Service
public class NoticeBulletinServiceImpl implements NoticeBulletinService{

	@Autowired
	private NoticeBulletinDao noticeBulltinDao;
	
	@Transactional(readOnly = true)
	@Override
	public PageInfo<NoticeBulletin> getAll(Integer pageNo)
	{
		PageHelper.startPage(pageNo, 15);
		List<NoticeBulletin> list = noticeBulltinDao.getAll();
		PageInfo<NoticeBulletin> pageInfo = new PageInfo<NoticeBulletin>(list);
		return pageInfo;
	}

	@Transactional(readOnly = true)
	@Override
	public List<NoticeBulletin> getNoticeBulletinByDate() {
		return noticeBulltinDao.getNoticeBulletinByDate();
	}

	@Transactional
	@Override
	public Integer insertNoticeBulletin(NoticeBulletin noticeBulletin) {
		Employee employee = (Employee) SecurityUtils.getSubject().getSession().getAttribute("employee");
		noticeBulletin.setCompany(employee.getDept().getCompany());
		noticeBulletin.setDept(employee.getDept());
		noticeBulletin.setEmployee(employee);
		return noticeBulltinDao.insertNoticeBulletin(noticeBulletin);
	}

	@Transactional
	@Override
	public Integer delNoticeBulletinById(Integer nbId) {
		return noticeBulltinDao.delNoticeBulletinById(nbId);
	}

	@Transactional
	@Override
	public Integer editNoticeBulletinById(NoticeBulletin noticeBulletin) {
		Employee employee = (Employee) SecurityUtils.getSubject().getSession().getAttribute("employee");
		noticeBulletin.setCompany(employee.getDept().getCompany());
		noticeBulletin.setDept(employee.getDept());
		noticeBulletin.setEmployee(employee);
		return noticeBulltinDao.editNoticeBulletinById(noticeBulletin);
	}
}
