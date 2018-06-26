package com.jm.entity.application;

/**
 * 名片印刷申请
 */
public class Card {

	private String apply; // 申请时间
	private String inlineRadioOptions2; // 紧急程度
	private String name; // 名片名称
	private String company; // 名片公司
	private String dept; // 名片部门
	private String manager; // 名片职务
	private String phone; // 名片手机
	private String tel; // 名片电话
	private String fax; // 名片传真
	private String email; // 名片邮箱
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Card(String apply, String inlineRadioOptions2, String name, String company, String dept, String manager,
			String phone, String tel, String fax, String email, String message, String applicant, String nextPerson,
			String state, String opinion) {
		super();
		this.apply = apply;
		this.inlineRadioOptions2 = inlineRadioOptions2;
		this.name = name;
		this.company = company;
		this.dept = dept;
		this.manager = manager;
		this.phone = phone;
		this.tel = tel;
		this.fax = fax;
		this.email = email;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
	}

	public Card() {
		super();
	}

	@Override
	public String toString() {
		return "Card [apply=" + apply + ", inlineRadioOptions2=" + inlineRadioOptions2 + ", name=" + name + ", company="
				+ company + ", dept=" + dept + ", manager=" + manager + ", phone=" + phone + ", tel=" + tel + ", fax="
				+ fax + ", email=" + email + ", message=" + message + ", applicant=" + applicant + ", nextPerson="
				+ nextPerson + ", state=" + state + ", opinion=" + opinion + "]";
	}

}
