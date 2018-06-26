package com.jm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 部门实体类
 */
@SuppressWarnings("serial")
public class Department implements Serializable {

	private Integer dId; // 部门编号
	private String dName; // 部门名称
	private String dAddress; // 部门地址
	private String dPhone; // 部门电话
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date dHiredate; // 部门成立日期
	private Company company; // 部门所属公司

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getdAddress() {
		return dAddress;
	}

	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}

	public String getdPhone() {
		return dPhone;
	}

	public void setdPhone(String dPhone) {
		this.dPhone = dPhone;
	}

	public Date getdHiredate() {
		return dHiredate;
	}

	public void setdHiredate(Date dHiredate) {
		this.dHiredate = dHiredate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Department(Integer dId, String dName, String dAddress, String dPhone, Date dHiredate, Company company) {
		super();
		this.dId = dId;
		this.dName = dName;
		this.dAddress = dAddress;
		this.dPhone = dPhone;
		this.dHiredate = dHiredate;
		this.company = company;
	}

	public Department(String dName, String dAddress, String dPhone, Date dHiredate, Company company) {
		super();
		this.dName = dName;
		this.dAddress = dAddress;
		this.dPhone = dPhone;
		this.dHiredate = dHiredate;
		this.company = company;
	}

	public Department() {
		super();
	}

	@Override
	public String toString() {
		return "Department [dId=" + dId + ", dName=" + dName + ", dAddress=" + dAddress + ", dPhone=" + dPhone
				+ ", dHiredate=" + dHiredate + ", company=" + company + "]";
	}

}
