package com.jm.entity.application;

/**
 * 超额补贴申请
 */
public class Excess {

	private String apply; // 申请时间
	private String inlineRadioOptions2; // 紧急程度
	private String region; // 出差区域
	private String monthlyTask; // 月任务
	private String monthlyReturn; // 月回款
	private String completionRetio; // 完成比例
	private String subsidy; // 增加补贴标准
	private String daysOfTravel; // 出差天数
	private String subsidizedAmouny; // 应补贴金额
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMonthlyTask() {
		return monthlyTask;
	}

	public void setMonthlyTask(String monthlyTask) {
		this.monthlyTask = monthlyTask;
	}

	public String getMonthlyReturn() {
		return monthlyReturn;
	}

	public void setMonthlyReturn(String monthlyReturn) {
		this.monthlyReturn = monthlyReturn;
	}

	public String getCompletionRetio() {
		return completionRetio;
	}

	public void setCompletionRetio(String completionRetio) {
		this.completionRetio = completionRetio;
	}

	public String getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}

	public String getDaysOfTravel() {
		return daysOfTravel;
	}

	public void setDaysOfTravel(String daysOfTravel) {
		this.daysOfTravel = daysOfTravel;
	}

	public String getSubsidizedAmouny() {
		return subsidizedAmouny;
	}

	public void setSubsidizedAmouny(String subsidizedAmouny) {
		this.subsidizedAmouny = subsidizedAmouny;
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

	public Excess(String apply, String inlineRadioOptions2, String region, String monthlyTask, String monthlyReturn,
			String completionRetio, String subsidy, String daysOfTravel, String subsidizedAmouny, String message,
			String applicant, String nextPerson, String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.region = region;
		this.monthlyTask = monthlyTask;
		this.monthlyReturn = monthlyReturn;
		this.completionRetio = completionRetio;
		this.subsidy = subsidy;
		this.daysOfTravel = daysOfTravel;
		this.subsidizedAmouny = subsidizedAmouny;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Excess() {
		super();
	}

	@Override
	public String toString() {
		return "Excess [apply=" + apply + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", region=" + region
				+ ", monthlyTask=" + monthlyTask + ", monthlyReturn=" + monthlyReturn + ", completionRetio="
				+ completionRetio + ", subsidy=" + subsidy + ", daysOfTravel=" + daysOfTravel + ", subsidizedAmouny="
				+ subsidizedAmouny + ", message=" + message + ", applicant=" + applicant + ", nextPerson=" + nextPerson
				+ ", state=" + state + ", opinion=" + opinion + "]";
	}

}
