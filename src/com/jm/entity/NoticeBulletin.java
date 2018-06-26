package com.jm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 通知公告实体
 */
@SuppressWarnings("serial")
public class NoticeBulletin implements Serializable {

	private Integer nbId; // 通知公告编号
	private String nbTitle; // 通知公告标题
	private String nbContent; // 通知公告内容
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nbDate; // 通知公告发布日期
	private Company company; // 通知公告发布公司
	private Department dept; // 通知公告发布部门
	private Employee employee; // 通知公告发布人

	public Integer getNbId() {
		return nbId;
	}

	public void setNbId(Integer nbId) {
		this.nbId = nbId;
	}

	public String getNbTitle() {
		return nbTitle;
	}

	public void setNbTitle(String nbTitle) {
		this.nbTitle = nbTitle;
	}

	public String getNbContent() {
		return nbContent;
	}

	public void setNbContent(String nbContent) {
		this.nbContent = nbContent;
	}

	public Date getNbDate() {
		return nbDate;
	}

	public void setNbDate(Date nbDate) {
		this.nbDate = nbDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public NoticeBulletin(Integer nbId, String nbTitle, String nbContent, Date nbDate, Company company, Department dept,
			Employee employee) {
		super();
		this.nbId = nbId;
		this.nbTitle = nbTitle;
		this.nbContent = nbContent;
		this.nbDate = nbDate;
		this.company = company;
		this.dept = dept;
		this.employee = employee;
	}

	public NoticeBulletin(String nbTitle, String nbContent, Date nbDate, Company company, Department dept,
			Employee employee) {
		super();
		this.nbTitle = nbTitle;
		this.nbContent = nbContent;
		this.nbDate = nbDate;
		this.company = company;
		this.dept = dept;
		this.employee = employee;
	}

	public NoticeBulletin() {
		super();
	}

	@Override
	public String toString() {
		return "NoticeBulletin [nbId=" + nbId + ", nbTitle=" + nbTitle + ", nbContent=" + nbContent + ", nbDate="
				+ nbDate + ", company=" + company + ", dept=" + dept + ", employee=" + employee + "]";
	}

}
