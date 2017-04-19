package org.platform.snail.beans;

public class AceTree implements java.io.Serializable{
	private String id;
	private String name;
	private String type;
	private AdditionalParameters additionalParameters;
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
	public AdditionalParameters getAdditionalParameters() {
		return additionalParameters;
	}
	public void setAdditionalParameters(AdditionalParameters additionalParameters) {
		this.additionalParameters = additionalParameters;
	}
	
}
