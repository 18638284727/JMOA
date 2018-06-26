package com.jm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 权限实体类
 */
@SuppressWarnings("serial")
public class Jurisdiction implements Serializable{

	private Integer jId; // 权限编号
	private String jName; // 权限名称
	private String jDesc; // 权限描述
	private String jUrl; // 权限链接
	private String jIcon; // 权限图标
	private String jInterceptorRule; // 权限拦截规则
	private Integer jIsParent; // 是否父节点
	private Integer jParent; // 父节点
	private String jExt; // 预留字段
	private Integer jOrder; // 排序号
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date jDate; // 创建时间
	private List<Jurisdiction> children; // 该节点的所有子节点
	private String roles; // 拥有该节点的角色集合字符串

	public Integer getjId() {
		return jId;
	}

	public void setjId(Integer jId) {
		this.jId = jId;
	}

	public String getjName() {
		return jName;
	}

	public void setjName(String jName) {
		this.jName = jName;
	}

	public String getjDesc() {
		return jDesc;
	}

	public void setjDesc(String jDesc) {
		this.jDesc = jDesc;
	}

	public String getjUrl() {
		return jUrl;
	}

	public void setjUrl(String jUrl) {
		this.jUrl = jUrl;
	}

	public String getjIcon() {
		return jIcon;
	}

	public void setjIcon(String jIcon) {
		this.jIcon = jIcon;
	}

	public String getjInterceptorRule() {
		return jInterceptorRule;
	}

	public void setjInterceptorRule(String jInterceptorRule) {
		this.jInterceptorRule = jInterceptorRule;
	}

	public Integer getjIsParent() {
		return jIsParent;
	}

	public void setjIsParent(Integer jIsParent) {
		this.jIsParent = jIsParent;
	}

	public Integer getjParent() {
		return jParent;
	}

	public void setjParent(Integer jParent) {
		this.jParent = jParent;
	}

	public String getjExt() {
		return jExt;
	}

	public void setjExt(String jExt) {
		this.jExt = jExt;
	}

	public Integer getjOrder() {
		return jOrder;
	}

	public void setjOrder(Integer jOrder) {
		this.jOrder = jOrder;
	}

	public Date getjDate() {
		return jDate;
	}

	public void setjDate(Date jDate) {
		this.jDate = jDate;
	}

	public List<Jurisdiction> getChildren() {
		return children;
	}

	public void setChildren(List<Jurisdiction> children) {
		this.children = children;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Jurisdiction(Integer jId, String jName, String jDesc, String jUrl, String jIcon, String jInterceptorRule,
			Integer jIsParent, Integer jParent, String jExt, Integer jOrder, Date jDate, List<Jurisdiction> children,
			String roles) {
		super();
		this.jId = jId;
		this.jName = jName;
		this.jDesc = jDesc;
		this.jUrl = jUrl;
		this.jIcon = jIcon;
		this.jInterceptorRule = jInterceptorRule;
		this.jIsParent = jIsParent;
		this.jParent = jParent;
		this.jExt = jExt;
		this.jOrder = jOrder;
		this.jDate = jDate;
		this.children = children;
		this.roles = roles;
	}

	public Jurisdiction(String jName, String jDesc, String jUrl, String jIcon, String jInterceptorRule,
			Integer jIsParent, Integer jParent, String jExt, Integer jOrder, Date jDate, List<Jurisdiction> children,
			String roles) {
		super();
		this.jName = jName;
		this.jDesc = jDesc;
		this.jUrl = jUrl;
		this.jIcon = jIcon;
		this.jInterceptorRule = jInterceptorRule;
		this.jIsParent = jIsParent;
		this.jParent = jParent;
		this.jExt = jExt;
		this.jOrder = jOrder;
		this.jDate = jDate;
		this.children = children;
		this.roles = roles;
	}

	public Jurisdiction() {
		super();
	}

	@Override
	public String toString() {
		return "Jurisdiction [jId=" + jId + ", jName=" + jName + ", jDesc=" + jDesc + ", jUrl=" + jUrl + ", jIcon="
				+ jIcon + ", jInterceptorRule=" + jInterceptorRule + ", jIsParent=" + jIsParent + ", jParent=" + jParent
				+ ", jExt=" + jExt + ", jOrder=" + jOrder + ", jDate=" + jDate + ", children=" + children + ", roles="
				+ roles + "]";
	}

}
