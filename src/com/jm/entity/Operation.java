package com.jm.entity;

import java.io.Serializable;

/**
 * 操作实体类，用户所拥有的操作
 */
@SuppressWarnings("serial")
public class Operation implements Serializable{

	private Integer oId; // 编号
	private String oName; // 所操作的名称

	public Integer getoId() {
		return oId;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

}
