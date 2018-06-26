package com.jm.entity.application;

import java.util.List;

/**
 * 办公用品申请
 */
public class Office {

	private String apply; // 申请时间
	private String inlineRadioOptions2; // 紧急程度
	private List<TableData> tableDatas; // 表格数据
	private String message; // 消息
	private String applicant; // 当前申请人
	private String nextPerson; // 下级处理人
	private String state; // 状态
	private String opinion; // 意见

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getInlineRadioOptions2() {
		return inlineRadioOptions2;
	}

	public void setInlineRadioOptions2(String inlineRadioOptions2) {
		this.inlineRadioOptions2 = inlineRadioOptions2;
	}

	public List<TableData> getTableData() {
		return tableDatas;
	}

	public void setTableData(List<TableData> tableDatas) {
		this.tableDatas = tableDatas;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getNextPerson() {
		return nextPerson;
	}

	public void setNextPerson(String nextPerson) {
		this.nextPerson = nextPerson;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Office(String apply, String inlineRadioOptions2, List<TableData> tableDatas, String message, String applicant,
			String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.tableDatas = tableDatas;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Office() {
		super();
	}

	@Override
	public String toString() {
		return "Office [apply=" + apply + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", tableData=" + tableDatas
				+ ", message=" + message + ", applicant=" + applicant + ", nextPerson=" + nextPerson + ", state="
				+ state + ", opinion=" + opinion + "]";
	}

}
