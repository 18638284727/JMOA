package com.jm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公司实体类
 */
@SuppressWarnings("serial")
public class Company implements Serializable {

	private Integer cId; // 公司编号
	private String cName; // 公司名称
	private String cAddress; // 公司地址
	private String cPhone; // 公司电话
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date cHiredate; // 公司成立日期

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public Date getcHiredate() {
		return cHiredate;
	}

	public void setcHiredate(Date cHiredate) {
		this.cHiredate = cHiredate;
	}

	public Company(Integer cId, String cName, String cAddress, String cPhone, Date cHiredate) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cAddress = cAddress;
		this.cPhone = cPhone;
		this.cHiredate = cHiredate;
	}

	public Company(String cName, String cAddress, String cPhone, Date cHiredate) {
		super();
		this.cName = cName;
		this.cAddress = cAddress;
		this.cPhone = cPhone;
		this.cHiredate = cHiredate;
	}

	public Company() {
		super();
	}

	@Override
	public String toString() {
		return "Company [cId=" + cId + ", cName=" + cName + ", cAddress=" + cAddress + ", cPhone=" + cPhone
				+ ", cHiredate=" + cHiredate + "]";
	}

}
