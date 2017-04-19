package org.platform.snail.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.platform.snail.model.Department;
import org.platform.snail.model.Resources;
import org.platform.snail.model.Users;

public class SystemUser implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Users users;
	
	private Department department;
	
	private List<Resources> resources=new ArrayList<Resources>();
	
	private Map<String,Resources> map=new HashMap<String,Resources>();
	
	private String ip; 
	
	private Leader leader1;
	private Leader leader2;
	private Leader leader3;
	private Leader leader4;
	private Leader leader5;
	
	private String dpFullName;
	private String leaderFullName;
	private Map<String,Object> other=new HashMap<String,Object>();
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Resources> getResources() {
		return resources;
	}
	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}
	public Map<String, Resources> getMap() {
		return map;
	}
	public void setMap(Map<String, Resources> map) {
		this.map = map;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Leader getLeader1() {
		return leader1;
	}
	public void setLeader1(Leader leader1) {
		this.leader1 = leader1;
	}
	public Leader getLeader2() {
		return leader2;
	}
	public void setLeader2(Leader leader2) {
		this.leader2 = leader2;
	}
	public Leader getLeader3() {
		return leader3;
	}
	public void setLeader3(Leader leader3) {
		this.leader3 = leader3;
	}
	public Leader getLeader4() {
		return leader4;
	}
	public void setLeader4(Leader leader4) {
		this.leader4 = leader4;
	}
	public Leader getLeader5() {
		return leader5;
	}
	public void setLeader5(Leader leader5) {
		this.leader5 = leader5;
	}
	public String getDpFullName() {
		return dpFullName;
	}
	public void setDpFullName(String dpFullName) {
		this.dpFullName = dpFullName;
	}
	public String getLeaderFullName() {
		return leaderFullName;
	}
	public void setLeaderFullName(String leaderFullName) {
		this.leaderFullName = leaderFullName;
	}
	public Map<String, Object> getOther() {
		return other;
	}
	public void setOther(Map<String, Object> other) {
		this.other = other;
	}
	
}
