package com.jm.entity.application;

/**
 * 离职申请
 */
public class Quit {

	private String apply; // 申请时间
	private String inlineRadioOptions1; // 是否借款
	private String inlineRadioOptions2; // 紧急程度

	private String hiredate; // 入职时间
	private String quitdate; // 离职时间
	private String reason; // 原因
	private String explain; // 说明

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

	public String getInlineRadioOptions1() {
		return inlineRadioOptions1;
	}

	public void setInlineRadioOptions1(String inlineRadioOptions1) {
		this.inlineRadioOptions1 = inlineRadioOptions1;
	}

	public String getInlineRadioOptions2() {
		return inlineRadioOptions2;
	}

	public void setInlineRadioOptions2(String inlineRadioOptions2) {
		this.inlineRadioOptions2 = inlineRadioOptions2;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getQuitdate() {
		return quitdate;
	}

	public void setQuitdate(String quitdate) {
		this.quitdate = quitdate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
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

	public Quit(String apply, String inlineRadioOptions1, String inlineRadioOptions2, String hiredate, String quitdate,
			String reason, String explain, String tableData, String tableData1, String message, String applicant,
			String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions1 = inlineRadioOptions1;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.hiredate = hiredate;
		this.quitdate = quitdate;
		this.reason = reason;
		this.explain = explain;
		this.tableData = tableData;
		this.tableData1 = tableData1;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Quit() {
		super();
	}

	@Override
	public String toString() {
		return "Quit [apply=" + apply + ", inlineRadioOptions1=" + inlineRadioOptions1 + ", inlineRadioOptions2="
				+ inlineRadioOptions2 + ", hiredate=" + hiredate + ", quitdate=" + quitdate + ", reason=" + reason
				+ ", explain=" + explain + ", tableData=" + tableData + ", tableData1=" + tableData1 + ", message="
				+ message + ", applicant=" + applicant + ", nextPerson=" + nextPerson + ", state=" + state
				+ ", opinion=" + opinion + "]";
	}

}
