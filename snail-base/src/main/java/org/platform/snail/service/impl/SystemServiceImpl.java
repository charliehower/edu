package org.platform.snail.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.jasig.cas.client.util.CommonUtils;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.beans.Tree;
import org.platform.snail.dao.SystemDao;
import org.platform.snail.model.Department;
import org.platform.snail.model.Resources;
import org.platform.snail.model.Users;
import org.platform.snail.service.CacheService;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.service.EmailServiceInterface;
import org.platform.snail.service.SystemService;
import org.platform.snail.utils.CommonKeys;
import org.platform.snail.utils.CommonTreeUtils;
import org.platform.snail.utils.Config;
import org.platform.snail.utils.SnailBeanUtils;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
@Service("systemService")
public class SystemServiceImpl implements SystemService{
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SystemDao systemDao;
	
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private EmailServiceInterface emailService;
	@Autowired
	private CacheService cacheService;
	public  SystemUser getSystemUser(String userId,Map<String,String> cfg){	
		String portal=Config.getProperty("portal.type");
		SystemUser systemUser=new SystemUser();
		Users users=this.systemDao.selectUsersByUserId(userId);
		//List<Resources> resources=this.systemDao.selectResourcesByUserId(userId,portal);
		String departmentId=users.getDepartmentId();
		Department department=this.systemDao.selectDepartmentByDepartmentId(departmentId);
		systemUser.setUsers(users);
		systemUser.setDepartment(department);
		//systemUser.setResources(resources);
		systemUser.getOther().put("discriblineClassName", this.systemDao.selectClassesNamesByUserId(cfg.get(CommonKeys.cyear), systemUser.getUsers().getUserId()));
		systemUser.getOther().put("headmasterClassName", this.systemDao.selectHeadmasterClassesNamesByUserId(cfg.get(CommonKeys.cyear), systemUser.getUsers().getUserId()));
		this.createUserWorkflowInfo(systemUser);
		this.logger.debug(users.toString());
		this.logger.debug(department.toString());
		return systemUser;
	}
	
	public  SystemUser getSystemUserByAccount(String account,Map<String,String> cfg){	
		String portal=Config.getProperty("portal.type");
		Map<String,Resources> map=new HashMap<String,Resources>();
		SystemUser systemUser=new SystemUser();
		Users users=this.systemDao.selectUsersByAccount(account);
		List<Resources> resources=this.systemDao.selectResourcesByUserId(users.getUserId(),portal);
		String departmentId=users.getDepartmentId();
		Department department=this.systemDao.selectDepartmentByDepartmentId(departmentId);
		systemUser.setUsers(users);
		systemUser.setDepartment(department);
		systemUser.setResources(resources);
		for(Resources o:resources){
			map.put(o.getResourcesId(), o);
		}
		systemUser.setMap(map);
		systemUser.getOther().put("discriblineClassName", this.systemDao.selectClassesNamesByUserId(cfg.get(CommonKeys.cyear), systemUser.getUsers().getUserId()));
		systemUser.getOther().put("headmasterClassName", this.systemDao.selectHeadmasterClassesNamesByUserId(cfg.get(CommonKeys.cyear), systemUser.getUsers().getUserId()));
		this.createUserWorkflowInfo(systemUser);
		this.logger.debug(users.toString());
		this.logger.debug(department.toString());
		this.logger.debug(systemUser.toString());
		return systemUser;
	}
	private void createUserWorkflowInfo(SystemUser systemUser){
		if(systemUser.getDepartment().getDepartmentLevel().equals("5")){
			Department dm5=systemUser.getDepartment();
			systemUser.setLeader5(this.systemDao.selectLeaderByDepartmentId(dm5.getDepartmentId()));
			Department dm4=this.systemDao.selectDepartmentByDepartmentId(dm5.getParentDepartmentId());
			systemUser.setLeader4(this.systemDao.selectLeaderByDepartmentId(dm4.getDepartmentId()));
			Department dm3=this.systemDao.selectDepartmentByDepartmentId(dm4.getParentDepartmentId());
			systemUser.setLeader3(this.systemDao.selectLeaderByDepartmentId(dm3.getDepartmentId()));
			Department dm2=this.systemDao.selectDepartmentByDepartmentId(dm3.getParentDepartmentId());
			systemUser.setLeader2(this.systemDao.selectLeaderByDepartmentId(dm2.getDepartmentId()));
			Department dm1=this.systemDao.selectDepartmentByDepartmentId(dm2.getParentDepartmentId());
			systemUser.setLeader1(this.systemDao.selectLeaderByDepartmentId(dm1.getDepartmentId()));
		}
		if(systemUser.getDepartment().getDepartmentLevel().equals("4")){
			
			Department dm4=systemUser.getDepartment();
			systemUser.setLeader4(this.systemDao.selectLeaderByDepartmentId(dm4.getDepartmentId()));
			Department dm3=this.systemDao.selectDepartmentByDepartmentId(dm4.getParentDepartmentId());
			systemUser.setLeader3(this.systemDao.selectLeaderByDepartmentId(dm3.getDepartmentId()));
			Department dm2=this.systemDao.selectDepartmentByDepartmentId(dm3.getParentDepartmentId());
			systemUser.setLeader2(this.systemDao.selectLeaderByDepartmentId(dm2.getDepartmentId()));
			Department dm1=this.systemDao.selectDepartmentByDepartmentId(dm2.getParentDepartmentId());
			systemUser.setLeader1(this.systemDao.selectLeaderByDepartmentId(dm1.getDepartmentId()));
		}
		if(systemUser.getDepartment().getDepartmentLevel().equals("3")){
			Department dm3=systemUser.getDepartment();
			systemUser.setLeader3(this.systemDao.selectLeaderByDepartmentId(dm3.getDepartmentId()));
			Department dm2=this.systemDao.selectDepartmentByDepartmentId(dm3.getParentDepartmentId());
			systemUser.setLeader2(this.systemDao.selectLeaderByDepartmentId(dm2.getDepartmentId()));
			Department dm1=this.systemDao.selectDepartmentByDepartmentId(dm2.getParentDepartmentId());
			systemUser.setLeader1(this.systemDao.selectLeaderByDepartmentId(dm1.getDepartmentId()));
		}
		if(systemUser.getDepartment().getDepartmentLevel().equals("2")){
			Department dm2=systemUser.getDepartment();
			systemUser.setLeader2(this.systemDao.selectLeaderByDepartmentId(dm2.getDepartmentId()));
			Department dm1=this.systemDao.selectDepartmentByDepartmentId(dm2.getParentDepartmentId());
			systemUser.setLeader1(this.systemDao.selectLeaderByDepartmentId(dm1.getDepartmentId()));
		}
		if(systemUser.getDepartment().getDepartmentLevel().equals("1")){
			Department dm1=systemUser.getDepartment();
			systemUser.setLeader1(this.systemDao.selectLeaderByDepartmentId(dm1.getDepartmentId()));
		}
		StringBuffer a=new StringBuffer();
		StringBuffer b=new StringBuffer();
		
		
		
		if(systemUser.getLeader5()!=null){
			
			a.append(systemUser.getLeader5().getDepartmentName());
			a.append("/");
			
			b.append(systemUser.getLeader5().getLeaderName());
			b.append("/");
		}
		if(systemUser.getLeader4()!=null){
			
			a.append(systemUser.getLeader4().getDepartmentName());
			a.append("/");
			
			b.append(systemUser.getLeader4().getLeaderName());
			b.append("/");
		}
		if(systemUser.getLeader3()!=null){
			
			a.append(systemUser.getLeader3().getDepartmentName());
			a.append("/");
			
			b.append(systemUser.getLeader3().getLeaderName());
			b.append("/");
		}
		
		if(systemUser.getLeader2()!=null){
			
			a.append(systemUser.getLeader2().getDepartmentName());
			a.append("/");
			
			b.append(systemUser.getLeader2().getLeaderName());
			b.append("/");
		}
		if(systemUser.getLeader1()!=null){
			a.append(systemUser.getLeader1().getDepartmentName());
			b.append(systemUser.getLeader1().getLeaderName());
		}
		systemUser.setLeaderFullName(b.toString());
		systemUser.setDpFullName(a.toString());
	}
	public  List<Tree> getTreeList(List<Resources> resources,String id,boolean loadButton){
		TreeUtils treeUtils=new TreeUtils(resources,loadButton);
		return treeUtils.getTreeList(id);
	}
	public  Map<String,String> getButtonAuthor(List<Resources> resources,String id){
		Map<String,String> author=new HashMap<String,String>();
		TreeUtils treeUtils=new TreeUtils(resources,true);
		List<Resources>  list=treeUtils.getChildResourcesList(id);
		if(list!=null){
			for(Resources r:list){
				author.put(r.getResourcesUrl(), r.getResourcesName());
			}
		}
		return author;
	}
	public List<Tree> selectProvinceTreeList(){
		String key="C0001";
		if(cacheService.get(key)==null){
			CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.systemDao.selectProvinceTreeList());
			cacheService.put(key, commonTreeUtils.getTreeList("00"));
		}
		return (List<Tree>)cacheService.get(key);
	}
	
	public List<Tree> selectProvinceTreeList(String pid,boolean isRoot){
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.systemDao.selectProvinceTreeListByPid(pid));
		if(isRoot){
			//return commonTreeUtils.getTreeListCaseSelf(pid);
		}
		return commonTreeUtils.getTreeList(pid);
	}
	public  DataResponse updatePassword(String password,String repassword,SystemUser systemUser){
		
		if (SnailUtils.isBlankString(password)) {
			return new DataResponse(false,"密码不能为空！");
		}
		if (SnailUtils.isBlankString(repassword)) {
			return new DataResponse(false,"确认密码不能为空！");
		}
		if (!repassword.equals(password)) {
			return new DataResponse(false,"确认密码与密码不一致！");
		}
		password=SnailUtils.getMd5(password);
		this.systemDao.updatePassword(systemUser.getUsers().getUserId(), password);
		this.dataBaseLogService.log("用户密码修改", "用户密码", "", password, systemUser.getUsers().getUserId(),
				systemUser);
		return new DataResponse(true,"密码修改完成！");
	}
	public  Map<String,Object> selectDepartment(Map<String,String> params){
		 Map<String,Object> rst=new HashMap<String,Object>();
		 List<Map<String,String>> list=this.systemDao.selectDepartment(params);
		 rst.put("total", list.size());
		 rst.put("rows",list);
		 return rst;
	}
	public  Map<String,Object> selectUsers(Map<String,Object> params){
		 Map<String,Object> rst=new HashMap<String,Object>();
		 List<Map<String,String>> list=this.systemDao.selectUsers(params);
		 rst.put("total", list.size());
		 rst.put("rows",list);
		 return rst;
	}
	public  Map<String,Object> selectStudent(Map<String,Object> params){
		 Map<String,Object> rst=new HashMap<String,Object>();
		 List<Map<String,String>> list=this.systemDao.selectStudent(params);
		 rst.put("total", list.size());
		 rst.put("rows",list);
		 return rst;
	}
	public  Map<String,Object> getDiseaseListByPcodeOrName(Map<String,String> params){
		 Map<String,Object> rst=new HashMap<String,Object>();
		 List<Map<String,Object>> list=this.systemDao.getDiseaseListByPcodeOrName(params);
		 rst.put("total", list.size());
		 rst.put("rows",list);
		 return rst;
	}
	
	/* (non-Javadoc)
	 * @see org.platform.snail.service.SystemService#retrievePassword(java.lang.String)
	 */
	public DataResponse retrievePassword(String email) throws Exception{
		DataResponse rst = new DataResponse();
		if (CommonUtils.isBlank(email)) {
			return  new DataResponse(false,"Email不能为空！");
		}
		
		Users user=this.systemDao.selectUsersByEmail(email);
		if(user==null){
			return  new DataResponse(false,"不存在此用户的Email！");
		}
		String newPasswd=SnailUtils.genRandomNum(6);
		this.systemDao.updatePassword(user.getUserId(), SnailUtils.getMd5(newPasswd));
		List address=new ArrayList();
		address.add(email);
		String subject="",content="";
		VelocityEngine velocityEngine = new VelocityEngine();
    	velocityEngine.setProperty("resource.loader","class");
    	velocityEngine.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    	
        Map<String,Object> model = new HashMap<String,Object>();
        SnailBeanUtils.copyBean2Map(user, model);
        model.put("newPasswd", newPasswd);
        model.put("sysDate", new Date().toLocaleString());
        subject= VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"email.subject.vm", "UTF-8", model);
        content= VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"email.vm", "UTF-8", model);
        this.emailService.sendBatchEmail(subject, content, address);
		return new DataResponse(true,"密码已重新设置，请查收 "+email);
	}
	public  List<Tree> selectDepartmentTreeList(String id){
		CommonTreeUtils commonTreeUtils=new CommonTreeUtils(this.systemDao.selectDepartmentTreeList(id));
		return commonTreeUtils.getTreeListCaseSelf(id);
	}
	
	public  Map<String,Object> selectTeacher(Map<String,Object> params){
		 Map<String,Object> rst=new HashMap<String,Object>();
		 List<Map<String,String>> list=this.systemDao.selectTeacher(params);
		 rst.put("total", list.size());
		 rst.put("rows",list);
		 return rst;
	}
	public void loadConfig(ServletContext servletContext){
		List<org.platform.snail.model.Config> list=this.systemDao.loadConfig();
		Map<String,String> config=new HashMap<String,String>();
		for(org.platform.snail.model.Config cfg:list){
			config.put(cfg.getConfigKey(), cfg.getConfigValue());
		}
		
		servletContext.setAttribute(CommonKeys.cfg, config);
		this.logger.info(config);
		this.logger.info("loadConfig cfg complete");
	}
}
