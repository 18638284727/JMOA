package com.jm.entity.application;

import java.io.Serializable;

/**
 * 出差申请
 */
@SuppressWarnings("serial")
public class Travle implements Serializable {

	private String apply; // 申请时间
	private String startTime1; // 开始时间-年月日
	private String startTime2; // 开始时间-时分秒
	private String endTime1; // 结束时间-年月日
	private String endTime2; // 结束时间-时分秒
	private String numberOfDays; // 请假天数
	private String inlineRadioOptions1; // 是否借款
	private String inlineRadioOptions2; // 紧急程度
	private Double money; // 借款金额
	private String causeContent; // 原因内容

	private String project; // 关联项目
	private String customer; // 关联客户
	private String contract; // 关联合同

	private String tableData;
	private String tableData1;
	private String tableData2;
	private String tableData3;

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

	public String getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}

	public String getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}

	public String getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getCauseContent() {
		return causeContent;
	}

	public void setCauseContent(String causeContent) {
		this.causeContent = causeContent;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
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

	public String getTableData2() {
		return tableData2;
	}

	public void setTableData2(String tableData2) {
		this.tableData2 = tableData2;
	}

	public String getTableData3() {
		return tableData3;
	}

	public void setTableData3(String tableData3) {
		this.tableData3 = tableData3;
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

	public Travle(String apply, String startTime1, String startTime2, String endTime1, String endTime2,
			String numberOfDays, String inlineRadioOptions1, String inlineRadioOptions2, Double money,
			String causeContent, String project, String customer, String contract, String tableData, String tableData1,
			String tableData2, String tableData3, String message, String applicant, String nextPerson, String state,
			String opinion) {
		super();
		this.apply = apply;
		this.startTime1 = startTime1;
		this.startTime2 = startTime2;
		this.endTime1 = endTime1;
		this.endTime2 = endTime2;
		this.numberOfDays = numberOfDays;
		this.inlineRadioOptions1 = inlineRadioOptions1;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.money = money;
		this.causeContent = causeContent;
		this.project = project;
		this.customer = customer;
		this.contract = contract;
		this.tableData = tableData;
		this.tableData1 = tableData1;
		this.tableData2 = tableData2;
		this.tableData3 = tableData3;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Travle() {
		super();
	}

	@Override
	public String toString() {
		return "Travle [apply=" + apply + ", startTime1=" + startTime1 + ", startTime2=" + startTime2 + ", endTime1="
				+ endTime1 + ", endTime2=" + endTime2 + ", numberOfDays=" + numberOfDays + ", inlineRadioOptions1="
				+ inlineRadioOptions1 + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", money=" + money
				+ ", causeContent=" + causeContent + ", project=" + project + ", customer=" + customer + ", contract="
				+ contract + ", tableData=" + tableData + ", tableData1=" + tableData1 + ", tableData2=" + tableData2
				+ ", tableData3=" + tableData3 + ", message=" + message + ", applicant=" + applicant + ", nextPerson="
				+ nextPerson + ", state=" + state + ", opinion=" + opinion + "]";
	}

}
