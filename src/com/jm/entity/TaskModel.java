package com.jm.entity;

import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 流程任务实体类
 */
public class TaskModel {

	private String id; // 序号
	private String name; // 节点名称
	private String applicant; // 申请人
	private String assignee; // 当前处理人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime; // 创建时间
	private String processInstanceId; // 流程实例
	private String processDefinitionId; // 流程定义
	private String taskDefinitionKey; // task标识
	private Map<String, Object> variables; // 用来存储提交申请的表单数据

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public TaskModel(String id, String name, String applicant, String assignee, Date createTime,
			String processInstanceId, String processDefinitionId, String taskDefinitionKey,
			Map<String, Object> variables) {
		super();
		this.id = id;
		this.name = name;
		this.applicant = applicant;
		this.assignee = assignee;
		this.createTime = createTime;
		this.processInstanceId = processInstanceId;
		this.processDefinitionId = processDefinitionId;
		this.taskDefinitionKey = taskDefinitionKey;
		this.variables = variables;
	}

	public TaskModel() {
		super();
	}

	@Override
	public String toString() {
		return "TaskModel [id=" + id + ", name=" + name + ", applicant=" + applicant + ", assignee=" + assignee
				+ ", createTime=" + createTime + ", processInstanceId=" + processInstanceId + ", processDefinitionId="
				+ processDefinitionId + ", taskDefinitionKey=" + taskDefinitionKey + ", variables=" + variables + "]";
	}

}
