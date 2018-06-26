package com.jm.entity.application;

/**
 * 固定资产申请
 */
public class Assets {

	private String apply; // 申请时间
	private String inlineRadioOptions2; // 紧急程度
	private String aname; // 借出物品名称
	private String cause; // 借出原因
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

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
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

	public Assets(String apply, String inlineRadioOptions2, String aname, String cause, String message,
			String applicant, String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.aname = aname;
		this.cause = cause;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Assets() {
		super();
	}

	@Override
	public String toString() {
		return "Assets [apply=" + apply + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", aname=" + aname
				+ ", cause=" + cause + ", message=" + message + ", applicant=" + applicant + ", nextPerson="
				+ nextPerson + ", state=" + state + ", opinion=" + opinion + "]";
	}

}
