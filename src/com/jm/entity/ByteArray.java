package com.jm.entity;

/**
 * 二进制数据实体类，主要包含xml，图片信息
 */
public class ByteArray {

	private String id; // 主键
	private Integer rev; // 乐观锁
	private String name; // 名称
	private String deploymentId; // 部署编号(外键)
	private Object bytes; // 内容
	private Integer generated; // 1.用户上传 2.系统自动生成

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public Object getBytes() {
		return bytes;
	}

	public void setBytes(Object bytes) {
		this.bytes = bytes;
	}

	public Integer getGenerated() {
		return generated;
	}

	public void setGenerated(Integer generated) {
		this.generated = generated;
	}

	public ByteArray(String id, Integer rev, String name, String deploymentId, Object bytes, Integer generated) {
		super();
		this.id = id;
		this.rev = rev;
		this.name = name;
		this.deploymentId = deploymentId;
		this.bytes = bytes;
		this.generated = generated;
	}

	public ByteArray() {
		super();
	}

	@Override
	public String toString() {
		return "ByteArray [id=" + id + ", rev=" + rev + ", name=" + name + ", deploymentId=" + deploymentId + ", bytes="
				+ bytes + ", generated=" + generated + "]";
	}

}
