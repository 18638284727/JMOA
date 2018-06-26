package com.jm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 流程模型实体类
 */
@SuppressWarnings("serial")
public class ActivitiModel implements Serializable {

	private String id; // 模型标识
	private String name; // 模型名称
	private String key; // 模型 key值
	private String description; // 模型描述
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime; // 创建时间
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdateTime; // 最后修改时间
	private Integer version; // 版本号

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ActivitiModel(String id, String name, String key, String description, Date createTime, Date lastUpdateTime,
			Integer version) {
		super();
		this.id = id;
		this.name = name;
		this.key = key;
		this.description = description;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.version = version;
	}

	public ActivitiModel(String name, String key, String description, Date createTime, Date lastUpdateTime,
			Integer version) {
		super();
		this.name = name;
		this.key = key;
		this.description = description;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.version = version;
	}

	public ActivitiModel() {
		super();
	}

	@Override
	public String toString() {
		return "ActivitiModel [id=" + id + ", name=" + name + ", key=" + key + ", description=" + description
				+ ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + ", version=" + version + "]";
	}

}
