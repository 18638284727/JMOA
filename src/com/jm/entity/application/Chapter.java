package com.jm.entity.application;

/**
 * 用章申请
 */
public class Chapter {

	private String apply; // 申请时间
	private String inlineRadioOptions1; // 是否借款
	private String inlineRadioOptions2; // 紧急程度

	private String startTime1; // 开始时间
	private String endTime1; // 结束时间
	private String days; // 总共多少天

	private String tableData;

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

	public String getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTableData() {
		return tableData;
	}

	public void setTableData(String tableData) {
		this.tableData = tableData;
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

	public Chapter(String apply, String inlineRadioOptions1, String inlineRadioOptions2, String startTime1,
			String endTime1, String days, String tableData, String message, String applicant, String nextPerson,
			String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions1 = inlineRadioOptions1;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.startTime1 = startTime1;
		this.endTime1 = endTime1;
		this.days = days;
		this.tableData = tableData;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Chapter() {
		super();
	}

	@Override
	public String toString() {
		return "Chapter [apply=" + apply + ", inlineRadioOptions1=" + inlineRadioOptions1 + ", inlineRadioOptions2="
				+ inlineRadioOptions2 + ", startTime1=" + startTime1 + ", endTime1=" + endTime1 + ", days=" + days
				+ ", tableData=" + tableData + ", message=" + message + ", applicant=" + applicant + ", nextPerson="
				+ nextPerson + ", state=" + state + ", opinion=" + opinion + "]";
	}

}
