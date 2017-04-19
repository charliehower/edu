package org.platform.snail.workflow.vo;

import java.util.HashMap;
import java.util.Map;

public class TaskVo implements java.io.Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  java.lang.String Id;
	 
	private  java.lang.String name;
	  
	private  java.lang.String description;
	 
	private  java.lang.String assignee;
	  
	private  java.util.Date createTime;
	  
	private  java.util.Date duedate;
	  
	private  int priority;
	  
	private  java.lang.Integer progress;

	private  java.lang.String executionId;
	
	private  java.lang.String activityName;

	private  java.lang.String formResourceName;
	  
	  

	public java.lang.String getId() {
		return Id;
	}

	public void setId(java.lang.String id) {
		Id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getAssignee() {
		return assignee;
	}

	public void setAssignee(java.lang.String assignee) {
		this.assignee = assignee;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getDuedate() {
		return duedate;
	}

	public void setDuedate(java.util.Date duedate) {
		this.duedate = duedate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public java.lang.Integer getProgress() {
		return progress;
	}

	public void setProgress(java.lang.Integer progress) {
		this.progress = progress;
	}

	public java.lang.String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(java.lang.String executionId) {
		this.executionId = executionId;
	}

	public java.lang.String getActivityName() {
		return activityName;
	}

	public void setActivityName(java.lang.String activityName) {
		this.activityName = activityName;
	}

	public java.lang.String getFormResourceName() {
		return formResourceName;
	}

	public void setFormResourceName(java.lang.String formResourceName) {
		this.formResourceName = formResourceName;
	}
	private Map<String,?> variables=new HashMap<String,Object>();
	
	
	public Map<String, ?> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, ?> variables) {
		this.variables = variables;
	}

	@Override
	public String toString() {
		return "TaskVo [Id=" + Id + ", name=" + name + ", description="
				+ description + ", assignee=" + assignee + ", createTime="
				+ createTime + ", duedate=" + duedate + ", priority="
				+ priority + ", progress=" + progress + ", executionId="
				+ executionId + ", activityName=" + activityName
				+ ", formResourceName=" + formResourceName + "]";
	}
	  
	  
}
