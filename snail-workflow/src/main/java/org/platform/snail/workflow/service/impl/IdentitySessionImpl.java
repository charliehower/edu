package org.platform.snail.workflow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;
import org.platform.snail.utils.SpringUtils;
import org.platform.snail.workflow.dao.WorkflowDao;
import org.platform.snail.workflow.model.GroupImpl;
import org.platform.snail.workflow.model.UserImpl;

public class IdentitySessionImpl implements IdentitySession{
	private WorkflowDao workflowDao;
	Logger logger = Logger.getLogger(this.getClass());
	
	public IdentitySessionImpl(){
		this.logger.info("IdentitySessionImpl-> init ");
		this.workflowDao=(WorkflowDao)SpringUtils.getBean("workflowDao");
		
	}
	public String createGroup(String arg0, String arg1, String arg2) {
		this.logger.info("createGroup ");
		return null;
	}

	public void createMembership(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		this.logger.info("createMembership ");
	}

	public String createUser(String arg0, String arg1, String arg2, String arg3) {
		
		this.logger.info("createUser "+ new StringBuffer().append(arg0).append(arg1).append(arg2).append(arg3).toString());
		return null;
	}

	public void deleteGroup(String arg0) {
		// TODO Auto-generated method stub
		this.logger.info("deleteGroup ");
	}

	public void deleteMembership(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		this.logger.info("deleteMembership ");
	}

	public void deleteUser(String arg0) {
		// TODO Auto-generated method stub
		this.logger.info("deleteUser ");
	}

	public Group findGroupById(String arg0) {
		this.logger.info("findGroupById"+ arg0);
		return this.workflowDao.findGroupById(arg0);
	}

	public List<Group> findGroupsByUser(String arg0) {
		this.logger.info("findGroupsByUser->"+ arg0);
		List<Group> list=new ArrayList<Group>();
		List<GroupImpl> items=this.workflowDao.findGroupsByUser(arg0);
		for(Group e:items){
			list.add(e);
		}
		return list;
	}

	public List<Group> findGroupsByUserAndGroupType(String arg0, String arg1) {
		this.logger.info("findGroupsByUserAndGroupType"+ arg0);
		List<Group> list=new ArrayList<Group>();
		List<GroupImpl> items=this.workflowDao.findGroupsByUserAndGroupType(arg0, arg1);
		for(Group e:items){
			list.add(e);
		}
		return list;
	}

	public User findUserById(String arg0) {
		this.logger.info("findUserById"+ arg0);
		
		return this.workflowDao.findUserById(arg0);
	}

	public List<User> findUsers() {
		this.logger.info("findUsers");
		List<User> list=new ArrayList<User>();
		List<UserImpl> items=this.workflowDao.findUsers();
		for(User e:items){
			list.add(e);
		}
		return list;
	}

	public List<User> findUsersByGroup(String arg0) {
		this.logger.info("findUsersByGroup");
		List<User> list=new ArrayList<User>();
		List<UserImpl> items=this.workflowDao.findUsersByGroup(arg0);
		for(User e:items){
			list.add(e);
		}
		return list;
	}

	public List<User> findUsersById(String... arg0) {
		this.logger.info("findUsersById");
		List<User> list=new ArrayList<User>();
		for(String userId:arg0){
			this.logger.info("----------->"+userId);
			UserImpl userImpl=this.workflowDao.findUserById(userId);
			list.add(userImpl);
		}
		return list;
	}

}
