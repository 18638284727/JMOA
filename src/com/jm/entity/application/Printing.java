package com.jm.entity.application;

/**
 * 印刷品印刷申请
 */
public class Printing {

	private String apply; // 申请时间
	private String inlineRadioOptions2; // 紧急程度

	private String remark; // 备注
	private String tableData;
	private String tableData1;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTableData() {
		return tableData;
	}

	public void setTableData(String tableData) {
		this.tableData = tableData;
	}

	public String getTableData1() {
		return tableData1;
	}

	public void setTableData1(String tableData1) {
		this.tableData1 = tableData1;
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

	public Printing(String apply, String inlineRadioOptions2, String remark, String tableData, String tableData1,
			String message, String applicant, String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.remark = remark;
		this.tableData = tableData;
		this.tableData1 = tableData1;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Printing() {
		super();
	}

	@Override
	public String toString() {
		return "Printing [apply=" + apply + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", remark=" + remark
				+ ", tableData=" + tableData + ", tableData1=" + tableData1 + ", message=" + message + ", applicant="
				+ applicant + ", nextPerson=" + nextPerson + ", state=" + state + ", opinion=" + opinion + "]";
	}

}
