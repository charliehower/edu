/**
 * @Title: DocFlowTaskVo.java
 * @Package org.platform.snail.edu.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package org.platform.snail.edu.vo;

import org.platform.snail.edu.model.DocFlowTask;

/**
 * @ClassName: DocFlowTaskVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class DocFlowTaskVo extends DocFlowTask {
	private String name;
	
	private String docDept;

	private String docDate;

	private String docNo;

	private String nativeNo;

	private String title;

	private String deployDate;
	
	private String deployUser;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocDept() {
		return docDept;
	}

	public void setDocDept(String docDept) {
		this.docDept = docDept;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getNativeNo() {
		return nativeNo;
	}

	public void setNativeNo(String nativeNo) {
		this.nativeNo = nativeNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeployDate() {
		return deployDate;
	}

	public void setDeployDate(String deployDate) {
		this.deployDate = deployDate;
	}

	public String getDeployUser() {
		return deployUser;
	}

	public void setDeployUser(String deployUser) {
		this.deployUser = deployUser;
	}

}
