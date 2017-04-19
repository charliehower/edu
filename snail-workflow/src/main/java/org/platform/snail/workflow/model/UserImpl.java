package org.platform.snail.workflow.model;

import java.io.Serializable;

import org.jbpm.api.identity.User;

public class UserImpl implements Serializable, User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String businessEmail;
	private String familyName;
	private String givenName;
	private String id;
	public String getBusinessEmail() {
		return businessEmail;
	}
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UserImpl [businessEmail=" + businessEmail + ", familyName="
				+ familyName + ", givenName=" + givenName + ", id=" + id + "]";
	}

	
}
