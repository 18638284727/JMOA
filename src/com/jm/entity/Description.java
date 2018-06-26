package com.jm.entity;

public class Description {
	private String name;
	private Integer revision;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Description [name=" + name + "----- revision=" + revision + "----- description=" + description + "]";
	}
}
