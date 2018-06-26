package com.jm.entity;

/**
 * 流程定义实体类
 */
public class ProcessModel {

	private String id; // 流程定义编号
	private String name; // 流程定义名称
	private String key; // 流程定义key值
	private Integer version; // 流程定义版本
	private String deploymentId; // 流程定义部署编号
	private String resourceName; // 流程定义的xml文件
	private String diagramResourceName; // 流程定义的png图片

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDiagramResourceName() {
		return diagramResourceName;
	}

	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}

	public ProcessModel(String id, String name, String key, Integer version, String deploymentId, String resourceName,
			String diagramResourceName) {
		super();
		this.id = id;
		this.name = name;
		this.key = key;
		this.version = version;
		this.deploymentId = deploymentId;
		this.resourceName = resourceName;
		this.diagramResourceName = diagramResourceName;
	}

	public ProcessModel() {
		super();
	}

	@Override
	public String toString() {
		return "processModel [id=" + id + ", name=" + name + ", key=" + key + ", version=" + version + ", deploymentId="
				+ deploymentId + ", resourceName=" + resourceName + ", diagramResourceName=" + diagramResourceName
				+ "]";
	}

}
