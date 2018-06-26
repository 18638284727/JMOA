package com.jm.entity.application;

/**
 * 礼品申请
 */
public class Gift {

	private String apply; // 申请时间
	private String inlineRadioOptions2; // 紧急程度
	private String customer; // 客户名称
	private String manager; // 职务
	private String phone; // 电话
	private String money; // 预算
	private String causeContent; //
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

	public String getInlineRadioOptions2() {
		return inlineRadioOptions2;
	}

	public void setInlineRadioOptions2(String inlineRadioOptions2) {
		this.inlineRadioOptions2 = inlineRadioOptions2;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCauseContent() {
		return causeContent;
	}

	public void setCauseContent(String causeContent) {
		this.causeContent = causeContent;
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

	public Gift(String apply, String inlineRadioOptions2, String customer, String manager, String phone, String money,
			String causeContent, String tableData, String message, String applicant, String nextPerson, String state,
			String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.customer = customer;
		this.manager = manager;
		this.phone = phone;
		this.money = money;
		this.causeContent = causeContent;
		this.tableData = tableData;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Gift() {
		super();
	}

	@Override
	public String toString() {
		return "Gift [apply=" + apply + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", customer=" + customer
				+ ", manager=" + manager + ", phone=" + phone + ", money=" + money + ", causeContent=" + causeContent
				+ ", tableData=" + tableData + ", message=" + message + ", applicant=" + applicant + ", nextPerson="
				+ nextPerson + ", state=" + state + ", opinion=" + opinion + "]";
	}

}
