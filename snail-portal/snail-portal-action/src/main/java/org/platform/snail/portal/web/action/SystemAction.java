package org.platform.snail.portal.web.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.model.Resources;
import org.platform.snail.service.SystemService;
import org.platform.snail.utils.CommonKeys;
import org.platform.snail.utils.DictUtils;
import org.platform.snail.utils.JsonUtils;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/system")
public class SystemAction extends BaseController{
	private static final long serialVersionUID = 1L;
	@Autowired
	private SystemService systemService;
	

	@RequestMapping(value = "/updatePassword.do")
	@ResponseBody
	public DataResponse updatePassword(String password,String repassword) throws Exception {
		SystemUser systemUser =this.getSessionSystemUser();
		DataResponse rst=this.systemService.updatePassword(password, repassword, systemUser);
		return rst;
	}
	@RequestMapping(value = "/getTreeList.do")
	@ResponseBody
	public List<Tree> getTreeList(String loadButton)throws Exception {
		SystemUser systemUser =this.getSessionSystemUser();
		boolean lb=Boolean.valueOf(loadButton);
		List<Tree> list=this.systemService.getTreeList(systemUser.getResources(), "0",lb);
		return list;
	}
	@RequestMapping(value = "/getUrlMap.do",produces="text/plain;charset=utf-8")
	public ModelAndView getUrlMap()throws Exception {
			SystemUser systemUser =this.getSessionSystemUser();
		List<Resources> list=systemUser.getResources();
		Map<String,String> url=new HashMap<String,String>();
		StringBuffer sb=new StringBuffer("var map=");
		for(Resources resources:list){
			if(resources.getResourcesType().equals("2")){
				url.put(resources.getResourcesId(), resources.getResourcesUrl());
			}
		}
		sb.append(DictUtils.toJsonString(url));
		sb.append(";");
		this.logger.info(sb.toString());
		ModelAndView mav=new ModelAndView("js");
		mav.addObject("js",sb.toString());
		return mav;
	}
	@RequestMapping(value = "/getButtonAuthority.do",produces="text/plain;charset=utf-8")
	public ModelAndView getButtonAuthority(String id)throws Exception {
		SystemUser systemUser =this.getSessionSystemUser();
		List<Resources> list=systemUser.getResources();
		Map<String,String> author=this.systemService.getButtonAuthor(list, id);
		StringBuffer sb=new StringBuffer("var authorConfig=");
		sb.append(DictUtils.toJsonString(author));
		sb.append(";");
		this.logger.info(sb.toString());
		ModelAndView mav=new ModelAndView("js");
		mav.addObject("js",sb.toString());
		//mav.addObject(CommonKeys.CWebPath,SeachPathUtils.getPath(systemUser.getMap(), id));
		return mav;
	}
	@RequestMapping(value = "/getSessionSystemUser.do",produces="text/plain;charset=utf-8")
	public ModelAndView getSessionUser()throws Exception {
		SystemUser systemUser =this.getSessionSystemUser();
		SystemUser csystemUser=new SystemUser();
		csystemUser.setUsers(systemUser.getUsers());
		csystemUser.setDepartment(systemUser.getDepartment());
		csystemUser.setLeader1(systemUser.getLeader1());
		csystemUser.setLeader2(systemUser.getLeader2());
		csystemUser.setLeader3(systemUser.getLeader3());
		csystemUser.setLeader4(systemUser.getLeader4());
		csystemUser.setDpFullName(systemUser.getDpFullName());
		csystemUser.setLeaderFullName(systemUser.getLeaderFullName());
		StringBuffer sb=new StringBuffer("var systemUser=");
		sb.append(JsonUtils.toJSONString(csystemUser));
		sb.append(";");
		this.logger.info(sb.toString());
		ModelAndView mav=new ModelAndView("js");
		mav.addObject("js",sb.toString());
		return mav;
	}
	@RequestMapping(value = "/selectProvinceTreeList.do")
	@ResponseBody
	public List<Tree> selectProvinceTreeList(String id)throws Exception {
		boolean isRoot=false;
		if(SnailUtils.isBlankString(id)){
			 isRoot=true;
			 id="00";
		}
		List<Tree>	list=this.systemService.selectProvinceTreeList(id,isRoot);
		return list;
	}
	@RequestMapping(value = "/selectDepartment.do")
	@ResponseBody
	public Map<String,Object> selectDepartment(String q,String id)throws Exception {
		Map<String,String> params=new HashMap<String,String>();
		params.put("q", id);
		if(!SnailUtils.isBlankString(q)){
			params.put("q", q);
		}
		return this.systemService.selectDepartment(params);
	}
	@RequestMapping(value = "/loadDictByKey.do")
	@ResponseBody
	public List loadDictByKey(String key)throws Exception {
		Map<String, List> dictMap =(Map<String, List>)this.getRequest().getSession().getServletContext().getAttribute(CommonKeys.dictAppKey);
		return dictMap.get(key);
	}
	@RequestMapping(value = "/selectUsers.do")
	@ResponseBody
	public Map<String,Object> selectUsers(String q,String id)throws Exception {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("q", id);
		if(!SnailUtils.isBlankString(q)){
			params.put("q", q);
		}
		
		this.logger.info(params);
		return this.systemService.selectUsers(params);
	}
	@RequestMapping(value = "/selectStudent.do")
	@ResponseBody
	public Map<String,Object> selectStudent(String q,String id)throws Exception {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("q", id);
		if(!SnailUtils.isBlankString(q)){
			params.put("q", q);
		}
		
		this.logger.info(params);
		return this.systemService.selectStudent(params);
	}
	@RequestMapping(value = "/retrievePassword.do")
	@ResponseBody
	public DataResponse retrievePassword(String email)throws Exception {
		try{
			return this.systemService.retrievePassword(email);
		}catch(Exception e){
			return new DataResponse(false,"发送Email失败!",e.getMessage());
		}
		
	}
	@RequestMapping(value = "/getDiseaseListByPcodeOrName.do")
	@ResponseBody
	public Map<String,Object> getDiseaseListByPcodeOrName(String q,String id)throws Exception {
		Map<String,String> params=new HashMap<String,String>();
		params.put("q", id);
		if(!SnailUtils.isBlankString(q)){
			params.put("q", q);
		}
		this.logger.info(params);
		return this.systemService.getDiseaseListByPcodeOrName(params);
	}
	@RequestMapping(value = "/selectDepartmentTreeList.do")
	@ResponseBody
	public List<Tree> selectDepartmentTreeList(String id)throws Exception {
		List<Tree>	list=this.systemService.selectDepartmentTreeList(id);
		return list;
	}
	@RequestMapping(value = "/getSystemUser.do")
	@ResponseBody
	public SystemUser getSystemUser(String userId)throws Exception {
		Map<String,String> cfg=(Map<String,String>)this.getRequest().getSession().getServletContext().getAttribute(CommonKeys.cfg);
		return this.systemService.getSystemUser(userId,cfg);
	}
	@RequestMapping(value = "/selectTeacher.do")
	@ResponseBody
	public Map<String,Object> selectTeacher(HttpServletRequest request)throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();  
		@SuppressWarnings("unchecked")
		Enumeration<String> e=request.getParameterNames();
		while(e.hasMoreElements()){
			String key=(String)e.nextElement();
			String value=request.getParameter(key);
			params.put(key, value);
		}
		this.logger.info(params);
		return this.systemService.selectTeacher(params);
	}
}
