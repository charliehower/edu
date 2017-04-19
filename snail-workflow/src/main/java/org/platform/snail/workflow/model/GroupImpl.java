package org.platform.snail.workflow.model;

import java.io.Serializable;

import org.jbpm.api.identity.Group;

public class GroupImpl implements Serializable, Group {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GroupImpl [id=" + id + ", name=" + name + ", type=" + type
				+ "]";
	}

}
