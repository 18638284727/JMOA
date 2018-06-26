package com.jm.entity.application;

/**
 * 招聘申请
 */
public class Recruit {

	private String apply; // 申请时间
	private String inlineRadioOptions2; // 紧急程度
	private String inlineRadioOptions1; // 申请理由

	private String rdept; // 需要申请的部门
	private String rjob; // 需要申请的岗位
	private String rcount; // 需求人数
	private String organization; // 岗位编制
	private String numberOfPeople; // 实有人数
	private String rdate; // 到岗日期

	private String gender; // 申请人性别
	private String age; // 申请人年龄
	private String marriage; // 申请人婚姻状况
	private String education; // 学历
	private String major; // 专业
	private String experience; // 经验

	private String explain; // 说明
	private String duty; // 职责

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

	public String getInlineRadioOptions1() {
		return inlineRadioOptions1;
	}

	public void setInlineRadioOptions1(String inlineRadioOptions1) {
		this.inlineRadioOptions1 = inlineRadioOptions1;
	}

	public String getRdept() {
		return rdept;
	}

	public void setRdept(String rdept) {
		this.rdept = rdept;
	}

	public String getRjob() {
		return rjob;
	}

	public void setRjob(String rjob) {
		this.rjob = rjob;
	}

	public String getRcount() {
		return rcount;
	}

	public void setRcount(String rcount) {
		this.rcount = rcount;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(String numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
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

	public Recruit(String apply, String inlineRadioOptions2, String inlineRadioOptions1, String rdept, String rjob,
			String rcount, String organization, String numberOfPeople, String rdate, String gender, String age,
			String marriage, String education, String major, String experience, String explain, String duty,
			String message, String applicant, String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.inlineRadioOptions1 = inlineRadioOptions1;
		this.rdept = rdept;
		this.rjob = rjob;
		this.rcount = rcount;
		this.organization = organization;
		this.numberOfPeople = numberOfPeople;
		this.rdate = rdate;
		this.gender = gender;
		this.age = age;
		this.marriage = marriage;
		this.education = education;
		this.major = major;
		this.experience = experience;
		this.explain = explain;
		this.duty = duty;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Recruit() {
		super();
	}

	@Override
	public String toString() {
		return "Recruit [apply=" + apply + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", inlineRadioOptions1="
				+ inlineRadioOptions1 + ", rdept=" + rdept + ", rjob=" + rjob + ", rcount=" + rcount + ", organization="
				+ organization + ", numberOfPeople=" + numberOfPeople + ", rdate=" + rdate + ", gender=" + gender
				+ ", age=" + age + ", marriage=" + marriage + ", education=" + education + ", major=" + major
				+ ", experience=" + experience + ", explain=" + explain + ", duty=" + duty + ", message=" + message
				+ ", applicant=" + applicant + ", nextPerson=" + nextPerson + ", state=" + state + ", opinion="
				+ opinion + "]";
	}

}
