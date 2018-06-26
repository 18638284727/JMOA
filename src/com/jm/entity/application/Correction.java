package com.jm.entity.application;

/**
 * 员工转正申请
 */
public class Correction {

	private String apply; // 申请时间
	private String inlineRadioOptions1; //
	private String inlineRadioOptions2; // 紧急程度

	private String startTime1; // 试用期开始时间
	private String endTime1; // 试用期结束时间

	private String trialPost; //
	private Double probationSalary; // 试用薪资
	private Double expectationSalary; // 期望薪资
	private String selfEvaluation; // 自我工作评述

	private String tableData;
	private String tableData1;

	// 主管部门意见
	private String inlineRadioOptions3; // 主管评价结果
	private String inlineRadioOptions4; // 转正情况
	private String post; // 岗位
	private String standard; // 标准
	private String executionDate; // 执行日期
	private String deptOpinion; // 部门意见

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

	public String getTrialPost() {
		return trialPost;
	}

	public void setTrialPost(String trialPost) {
		this.trialPost = trialPost;
	}

	public Double getProbationSalary() {
		return probationSalary;
	}

	public void setProbationSalary(Double probationSalary) {
		this.probationSalary = probationSalary;
	}

	public Double getExpectationSalary() {
		return expectationSalary;
	}

	public void setExpectationSalary(Double expectationSalary) {
		this.expectationSalary = expectationSalary;
	}

	public String getSelfEvaluation() {
		return selfEvaluation;
	}

	public void setSelfEvaluation(String selfEvaluation) {
		this.selfEvaluation = selfEvaluation;
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

	public String getInlineRadioOptions3() {
		return inlineRadioOptions3;
	}

	public void setInlineRadioOptions3(String inlineRadioOptions3) {
		this.inlineRadioOptions3 = inlineRadioOptions3;
	}

	public String getInlineRadioOptions4() {
		return inlineRadioOptions4;
	}

	public void setInlineRadioOptions4(String inlineRadioOptions4) {
		this.inlineRadioOptions4 = inlineRadioOptions4;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	public String getDeptOpinion() {
		return deptOpinion;
	}

	public void setDeptOpinion(String deptOpinion) {
		this.deptOpinion = deptOpinion;
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

	public Correction(String apply, String inlineRadioOptions1, String inlineRadioOptions2, String startTime1,
			String endTime1, String trialPost, Double probationSalary, Double expectationSalary, String selfEvaluation,
			String tableData, String tableData1, String inlineRadioOptions3, String inlineRadioOptions4, String post,
			String standard, String executionDate, String deptOpinion, String message, String applicant,
			String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions1 = inlineRadioOptions1;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.startTime1 = startTime1;
		this.endTime1 = endTime1;
		this.trialPost = trialPost;
		this.probationSalary = probationSalary;
		this.expectationSalary = expectationSalary;
		this.selfEvaluation = selfEvaluation;
		this.tableData = tableData;
		this.tableData1 = tableData1;
		this.inlineRadioOptions3 = inlineRadioOptions3;
		this.inlineRadioOptions4 = inlineRadioOptions4;
		this.post = post;
		this.standard = standard;
		this.executionDate = executionDate;
		this.deptOpinion = deptOpinion;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Correction() {
		super();
	}

	@Override
	public String toString() {
		return "Correction [apply=" + apply + ", inlineRadioOptions1=" + inlineRadioOptions1 + ", inlineRadioOptions2="
				+ inlineRadioOptions2 + ", startTime1=" + startTime1 + ", endTime1=" + endTime1 + ", trialPost="
				+ trialPost + ", probationSalary=" + probationSalary + ", expectationSalary=" + expectationSalary
				+ ", selfEvaluation=" + selfEvaluation + ", tableData=" + tableData + ", tableData1=" + tableData1
				+ ", inlineRadioOptions3=" + inlineRadioOptions3 + ", inlineRadioOptions4=" + inlineRadioOptions4
				+ ", post=" + post + ", standard=" + standard + ", executionDate=" + executionDate + ", deptOpinion="
				+ deptOpinion + ", message=" + message + ", applicant=" + applicant + ", nextPerson=" + nextPerson
				+ ", state=" + state + ", opinion=" + opinion + "]";
	}

}
