package com.jm.entity;

import java.io.Serializable;

/**
 * 状态实体类
 */
@SuppressWarnings("serial")
public class State implements Serializable{
	
	private Integer sId; // 状态编号
	private String sName; // 状态名称

	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public State(Integer sId, String sName) {
		super();
		this.sId = sId;
		this.sName = sName;
	}

	public State() {
		super();
	}

	@Override
	public String toString() {
		return "State [sId=" + sId + ", sName=" + sName + "]";
	}

}
