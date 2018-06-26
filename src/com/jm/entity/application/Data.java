package com.jm.entity.application;

import java.util.LinkedHashMap;

/**
 * 表单数据传输
 */
public class Data {

	private String apply; // 申请时间
	private String inLineOptions1;
	private String inLineOptions2;
	private String inLineOptions3;
	private String inLineOptions4;

	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String reserve4;
	private String reserve5;
	private String reserve6;
	private String reserve7;
	private String reserve8;
	private String reserve9;
	private String reserve10;
	private String reserve11;
	private String reserve12;
	private String reserve13;
	private String reserve14;
	private String reserve15;

	private String tableData1;
	private String tableData2;
	private String tableData3;
	private String tableData4;

	private String message; // 消息
	private String applicant; // 当前申请人
	private String nextPerson; // 下级处理人
	private String state; // 状态
	private String opinion; // 意见
	private String opinions; // 存储所有审批的意见

	private String condition; // 互斥网关条件

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getInLineOptions1() {
		return inLineOptions1;
	}

	public void setInLineOptions1(String inLineOptions1) {
		this.inLineOptions1 = inLineOptions1;
	}

	public String getInLineOptions2() {
		return inLineOptions2;
	}

	public void setInLineOptions2(String inLineOptions2) {
		this.inLineOptions2 = inLineOptions2;
	}

	public String getInLineOptions3() {
		return inLineOptions3;
	}

	public void setInLineOptions3(String inLineOptions3) {
		this.inLineOptions3 = inLineOptions3;
	}

	public String getInLineOptions4() {
		return inLineOptions4;
	}

	public void setInLineOptions4(String inLineOptions4) {
		this.inLineOptions4 = inLineOptions4;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getReserve6() {
		return reserve6;
	}

	public void setReserve6(String reserve6) {
		this.reserve6 = reserve6;
	}

	public String getReserve7() {
		return reserve7;
	}

	public void setReserve7(String reserve7) {
		this.reserve7 = reserve7;
	}

	public String getReserve8() {
		return reserve8;
	}

	public void setReserve8(String reserve8) {
		this.reserve8 = reserve8;
	}

	public String getReserve9() {
		return reserve9;
	}

	public void setReserve9(String reserve9) {
		this.reserve9 = reserve9;
	}

	public String getReserve10() {
		return reserve10;
	}

	public void setReserve10(String reserve10) {
		this.reserve10 = reserve10;
	}

	public String getReserve11() {
		return reserve11;
	}

	public void setReserve11(String reserve11) {
		this.reserve11 = reserve11;
	}

	public String getReserve12() {
		return reserve12;
	}

	public void setReserve12(String reserve12) {
		this.reserve12 = reserve12;
	}

	public String getReserve13() {
		return reserve13;
	}

	public void setReserve13(String reserve13) {
		this.reserve13 = reserve13;
	}

	public String getReserve14() {
		return reserve14;
	}

	public void setReserve14(String reserve14) {
		this.reserve14 = reserve14;
	}

	public String getReserve15() {
		return reserve15;
	}

	public void setReserve15(String reserve15) {
		this.reserve15 = reserve15;
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

	public String getTableData4() {
		return tableData4;
	}

	public void setTableData4(String tableData4) {
		this.tableData4 = tableData4;
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

	public String getOpinions() {
		return opinions;
	}

	public void setOpinions(String opinions) {
		this.opinions = opinions;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Data(String apply, String inLineOptions1, String inLineOptions2, String inLineOptions3,
			String inLineOptions4, String reserve1, String reserve2, String reserve3, String reserve4, String reserve5,
			String reserve6, String reserve7, String reserve8, String reserve9, String reserve10, String reserve11,
			String reserve12, String reserve13, String reserve14, String reserve15, String tableData1,
			String tableData2, String tableData3, String tableData4, String message, String applicant,
			String nextPerson, String state, String opinion, String opinions, String condition) {
		super();
		this.apply = apply;
		this.inLineOptions1 = inLineOptions1;
		this.inLineOptions2 = inLineOptions2;
		this.inLineOptions3 = inLineOptions3;
		this.inLineOptions4 = inLineOptions4;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.reserve3 = reserve3;
		this.reserve4 = reserve4;
		this.reserve5 = reserve5;
		this.reserve6 = reserve6;
		this.reserve7 = reserve7;
		this.reserve8 = reserve8;
		this.reserve9 = reserve9;
		this.reserve10 = reserve10;
		this.reserve11 = reserve11;
		this.reserve12 = reserve12;
		this.reserve13 = reserve13;
		this.reserve14 = reserve14;
		this.reserve15 = reserve15;
		this.tableData1 = tableData1;
		this.tableData2 = tableData2;
		this.tableData3 = tableData3;
		this.tableData4 = tableData4;
		this.message = message;
		this.applicant = applicant;
		this.nextPerson = nextPerson;
		this.state = state;
		this.opinion = opinion;
		this.opinions = opinions;
		this.condition = condition;
	}

	public Data() {
		super();
	}

	@Override
	public String toString() {
		return "Data [apply=" + apply + ", inLineOptions1=" + inLineOptions1 + ", inLineOptions2=" + inLineOptions2
				+ ", inLineOptions3=" + inLineOptions3 + ", inLineOptions4=" + inLineOptions4 + ", reserve1=" + reserve1
				+ ", reserve2=" + reserve2 + ", reserve3=" + reserve3 + ", reserve4=" + reserve4 + ", reserve5="
				+ reserve5 + ", reserve6=" + reserve6 + ", reserve7=" + reserve7 + ", reserve8=" + reserve8
				+ ", reserve9=" + reserve9 + ", reserve10=" + reserve10 + ", reserve11=" + reserve11 + ", reserve12="
				+ reserve12 + ", reserve13=" + reserve13 + ", reserve14=" + reserve14 + ", reserve15=" + reserve15
				+ ", tableData1=" + tableData1 + ", tableData2=" + tableData2 + ", tableData3=" + tableData3
				+ ", tableData4=" + tableData4 + ", message=" + message + ", applicant=" + applicant + ", nextPerson="
				+ nextPerson + ", state=" + state + ", opinion=" + opinion + ", opinions=" + opinions + ", condition="
				+ condition + "]";
	}

}
