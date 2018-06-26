package com.jm.entity;

import java.io.Serializable;

/**
 * 民族实体类
 */
@SuppressWarnings("serial")
public class Nation implements Serializable{

	private Integer nId; // 民族编号
	private String nName; // 民族名称

	public Integer getnId() {
		return nId;
	}

	public void setnId(Integer nId) {
		this.nId = nId;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public Nation(Integer nId, String nName) {
		super();
		this.nId = nId;
		this.nName = nName;
	}

	public Nation() {
		super();
	}

	@Override
	public String toString() {
		return "Nation [nId=" + nId + ", nName=" + nName + "]";
	}

}
