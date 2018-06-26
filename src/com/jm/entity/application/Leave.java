package com.jm.entity.application;

/**
 * 请假申请
 */
public class Leave {

	private String apply; // 申请时间
	private String startTime1; // 开始时间-年月日
	private String startTime2; // 开始时间-时分秒
	private String endTime1; // 结束时间-年月日
	private String endTime2; // 结束时间-时分秒
	private String numberOfDays; // 请假天数
	private String inlineRadioOptions1; // 请假类型
	private String inlineRadioOptions2; // 紧急程度
	private String enclosure; // 附件
	private String causeContent; // 原因内容
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

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getCauseContent() {
		return causeContent;
	}

	public void setCauseContent(String causeContent) {
		this.causeContent = causeContent;
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

	public Leave(String apply, String startTime1, String startTime2, String endTime1, String endTime2,
			String numberOfDays, String inlineRadioOptions1, String inlineRadioOptions2, String enclosure,
			String causeContent, String message, String applicant, String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.startTime1 = startTime1;
		this.startTime2 = startTime2;
		this.endTime1 = endTime1;
		this.endTime2 = endTime2;
		this.numberOfDays = numberOfDays;
		this.inlineRadioOptions1 = inlineRadioOptions1;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.enclosure = enclosure;
		this.causeContent = causeContent;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Leave() {
		super();
	}

	@Override
	public String toString() {
		return "Leave [apply=" + apply + ", startTime1=" + startTime1 + ", startTime2=" + startTime2 + ", endTime1="
				+ endTime1 + ", endTime2=" + endTime2 + ", numberOfDays=" + numberOfDays + ", inlineRadioOptions1="
				+ inlineRadioOptions1 + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", enclosure=" + enclosure
				+ ", causeContent=" + causeContent + ", message=" + message + ", applicant=" + applicant
				+ ", nextPerson=" + nextPerson + ", state=" + state + ", opinion=" + opinion + "]";
	}

}
