/**
 * @Title: AssignTaskRepairs.java
 * @Package org.platform.snail.workflow.assigntask
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年1月22日 下午10:01:18
 * @version V1.0
 */

package org.platform.snail.workflow.assigntask;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import org.platform.snail.edu.dao.RepairsUsersMapper;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.utils.SpringUtils;
import org.platform.snail.workflow.service.WorkflowMsgService;
/**
 * @ClassName: AssignTaskRepairs
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年1月22日 下午10:01:18
 *
 */

public class AssignTaskRepairs implements AssignmentHandler {
	private static final long serialVersionUID = 1L;
	private String assignee="-1";
	Logger logger = Logger.getLogger(this.getClass());
	private RepairsUsersMapper repairsUsersMapper;
	private WorkflowMsgService workflowMsgService;
	
	public AssignTaskRepairs(){
		repairsUsersMapper=(RepairsUsersMapper)SpringUtils.getInstance().getBean("repairsUsersMapper");
		workflowMsgService=(WorkflowMsgService)SpringUtils.getInstance().getBean("workflowMsgService");
	}
	public void assign(Assignable assignable, OpenExecution execution)
			throws Exception {
		Map<String, ?> p = execution.getVariables();
		this.logger.info(p);
		if(p.containsKey("repairsCategory")){
			String repairsCategory=(String)p.get("repairsCategory");
			String locationId=(String)p.get("locationId");
			//总务报修->根据负责区域派工
			if(repairsCategory.equals("1")){
				assignee=repairsUsersMapper.selectOnlyUserIdByLocationId(locationId);
				//找不到->随机选择
				if(SnailUtils.isBlankString(assignee)){
					assignee=this.getRandomUser(repairsCategory);
				}
			}else{//电教报修，随机选择
				assignee=this.getRandomUser(repairsCategory);
			}
		}
		String id=execution.getProcessInstance().getId();
		assignable.setAssignee(assignee);
		this.workflowMsgService.repairs(id, assignee, "1");
		logger.info("assignee: " + assignee);
	}
	private String getRandomUser(String repairsCategory){
		
		List<Map<String,String>> list=repairsUsersMapper.selectUserListByCategoryId(repairsCategory);
		String [] assignee=new String[list.size()];
		int i=0;
		for(Map<String,String> a:list){
			assignee[i]=a.get("USERID");
			i++;
		}
		Random r = new Random();
		int random=r.nextInt(list.size());
		return assignee[random];
	}
	
	public static void main(String args[]){
		Random r = new Random();
		
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
		System.out.println(r.nextInt(6));
	
		System.out.println(r.nextInt(2));
		System.out.println(r.nextInt(2));
		System.out.println(r.nextInt(2));
		System.out.println(r.nextInt(2));
		System.out.println(r.nextInt(2));
		
	}
}
