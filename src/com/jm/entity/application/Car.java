package com.jm.entity.application;

/**
 * 
 * 用车申请实体
 *
 */
public class Car {

	private String apply; // 申请时间
	private String inlineRadioOptions2; // 紧急程度
	private String startTime1; // 开始时间-年月日
	private String startTime2; // 开始时间-时分秒
	private String endTime1; // 结束时间-年月日
	private String endTime2; // 结束时间-时分秒
	private String numberOfDays; // 用车天数
	private String inlineRadioOptions1; // 用车类型
	private String Cf; // 行程安排-出发地
	private String Md; // 行程安排-目的地
	private int Num = 0; // 随车人数
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

	public String getCf() {
		return Cf;
	}

	public void setCf(String cf) {
		Cf = cf;
	}

	public String getMd() {
		return Md;
	}

	public void setMd(String md) {
		Md = md;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
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

	public Car(String apply, String inlineRadioOptions2, String startTime1, String startTime2, String endTime1,
			String endTime2, String numberOfDays, String inlineRadioOptions1, String cf, String md, int num,
			String causeContent, String message, String applicant, String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.startTime1 = startTime1;
		this.startTime2 = startTime2;
		this.endTime1 = endTime1;
		this.endTime2 = endTime2;
		this.numberOfDays = numberOfDays;
		this.inlineRadioOptions1 = inlineRadioOptions1;
		Cf = cf;
		Md = md;
		Num = num;
		this.causeContent = causeContent;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Car() {
		super();
	}

	@Override
	public String toString() {
		return "Car [apply=" + apply + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", startTime1=" + startTime1
				+ ", startTime2=" + startTime2 + ", endTime1=" + endTime1 + ", endTime2=" + endTime2 + ", numberOfDays="
				+ numberOfDays + ", inlineRadioOptions1=" + inlineRadioOptions1 + ", Cf=" + Cf + ", Md=" + Md + ", Num="
				+ Num + ", causeContent=" + causeContent + ", message=" + message + ", applicant=" + applicant
				+ ", nextPerson=" + nextPerson + ", state=" + state + ", opinion=" + opinion + "]";
	}

}
