package com.jm.entity;

import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 历史任务实体类
 */
public class HistoryModel {

	private String id; // 编号
	private String name; // 名称
	private String assignee; // 处理人
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date startTime; // 开始时间
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date endTime; // 结束时间
	private String processInstanceId;
	private Map<String, Object> hisVariable; // 历史变量集合

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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Map<String, Object> getHisVariable() {
		return hisVariable;
	}

	public void setHisVariable(Map<String, Object> hisVariable) {
		this.hisVariable = hisVariable;
	}

	public HistoryModel(String id, String name, String assignee, Date startTime, Date endTime, String processInstanceId,
			Map<String, Object> hisVariable) {
		super();
		this.id = id;
		this.name = name;
		this.assignee = assignee;
		this.startTime = startTime;
		this.endTime = endTime;
		this.processInstanceId = processInstanceId;
		this.hisVariable = hisVariable;
	}

	public HistoryModel() {
		super();
	}

	@Override
	public String toString() {
		return "HistoryModel [id=" + id + ", name=" + name + ", assignee=" + assignee + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", processInstanceId=" + processInstanceId + ", hisVariable=" + hisVariable
				+ "]";
	}

}
