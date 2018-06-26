package com.jm.entity.application;

import java.io.Serializable;

/**
 * 表格数据
 */
@SuppressWarnings("serial")
public class TableData implements Serializable{

	private Integer Num; // 编号
	private String name; // 名称
	private String specifications; // 规格
	private Integer nums; // 数量
	private Double prices; // 价格
	private String purpose; // 用途
	private String requiredDate; // 需要日期
	private String user; // 使用人
	private String remarks; // 备注

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Double getPrices() {
		return prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public TableData(Integer Num, String name, String specifications, Integer nums, Double prices, String purpose,
			String requiredDate, String user, String remarks) {
		super();
		this.Num = Num;
		this.name = name;
		this.specifications = specifications;
		this.nums = nums;
		this.prices = prices;
		this.purpose = purpose;
		this.requiredDate = requiredDate;
		this.user = user;
		this.remarks = remarks;
	}

	public TableData() {
		super();
	}

	@Override
	public String toString() {
		return "TbaleData [Num=" + Num + ", name=" + name + ", specifications=" + specifications + ", nums=" + nums
				+ ", prices=" + prices + ", purpose=" + purpose + ", requiredDate=" + requiredDate + ", user=" + user
				+ ", remarks=" + remarks + "]";
	}

}
