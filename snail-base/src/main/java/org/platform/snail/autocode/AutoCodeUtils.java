/**
 * @Title: BeanUtils.java
 * @Package org.platform.snail.utils
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:15:42
 * @version V1.0
 */

package org.platform.snail.autocode;

/**
 * @ClassName: BeanUtils
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:15:42
 *
 */

/**
 * 
 */

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.utils.SnailUtils;

public class AutoCodeUtils {
    
  
    private String BEAN_SERVICE_TEMPLATE_VM_PATH;
    private String BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH ;
    private String BEAN_ACTION_TEMPLATE_VM_PATH;
    private String BEAN_QVO_PATH;
    private String BEAN_VO_PATH;
    
    private String ACTION_PATH ;
    private String SERVICE_PATH ;
    private String SERVICE_IMPL_PATH;
    
    private String ACTION_URL;
    private String SERVICE_URL;
    private String SERVICE_IMPL_URL;
    
    private String VO_PATH;
    private String VO_URL;
    
    private String BEAN_DAO_PATH;
    private String DAO_PATH;
    private String DAO_URL;

    
    private static Bean bean = new Bean();
    private static Annotation annotation = new Annotation();
    public AutoCodeUtils(){
    		this.BEAN_ACTION_TEMPLATE_VM_PATH=AutoCodeUtils.getProperty("BEAN_ACTION_TEMPLATE_VM_PATH");
        this. BEAN_SERVICE_TEMPLATE_VM_PATH = AutoCodeUtils.getProperty("BEAN_SERVICE_TEMPLATE_VM_PATH");
        this. BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH = AutoCodeUtils.getProperty("BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH");
        this. ACTION_PATH = AutoCodeUtils.getProperty("ACTION_PATH"); 
        this. SERVICE_PATH = AutoCodeUtils.getProperty("SERVICE_PATH");
        this. SERVICE_IMPL_PATH =AutoCodeUtils.getProperty("SERVICE_IMPL_PATH");
        
        this. ACTION_URL = AutoCodeUtils.getProperty("ACTION_URL");
        this. SERVICE_URL = AutoCodeUtils.getProperty("SERVICE_URL");
        this. SERVICE_IMPL_URL = AutoCodeUtils.getProperty("SERVICE_IMPL_URL");
        
        this.BEAN_QVO_PATH=AutoCodeUtils.getProperty("BEAN_QVO_PATH");
        this.BEAN_VO_PATH=AutoCodeUtils.getProperty("BEAN_VO_PATH");
        this.VO_PATH=AutoCodeUtils.getProperty("VO_PATH");
        this.VO_URL=AutoCodeUtils.getProperty("VO_URL");
        
        this.BEAN_DAO_PATH=AutoCodeUtils.getProperty("BEAN_DAO_PATH");
        this.DAO_PATH=AutoCodeUtils.getProperty("DAO_PATH");
        this.DAO_URL=AutoCodeUtils.getProperty("DAO_URL");
        
    }
    /**
     * 初始化bean和注解
     * @param c
     */
    public void init(Class c){
        if(c != null){
            String cName = c.getName();
            bean.setName(getLastChar(cName));
            bean.setBeanUrl(cName);
            bean.setLowerName(getLowercaseChar(getLastChar(cName)));

            annotation.setAuthorName("陈晓克");
            annotation.setAuthorMail("249134995@qq.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd");
            annotation.setDate(simpleDateFormat.format(new Date()));
            annotation.setVersion("1.0");
        }
    }
    
    /**
     * 创建bean的Dao<br>
     * 
     * @param c
     * @throws Exception
     */
    
    
    /**
     * 创建bean的Dao的实现<br>
     * 
     * @param c
     * @throws Exception
     */
    public void createAction(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/main/java/" + ACTION_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        
        String fileName = path + getLastChar(cName) + "Action.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        
       // bean.setBeanDaoUrl(ACTION_URL);
        //bean.setBeanDaoImplUrl(ACTION_IMPL_URL);
        
        fw.write(createCode(BEAN_ACTION_TEMPLATE_VM_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    
    public void createVO(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/main/java/" + VO_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        
        String fileName = path + getLastChar(cName) + "Vo.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        
       // bean.setBeanDaoUrl(ACTION_URL);
        //bean.setBeanDaoImplUrl(ACTION_IMPL_URL);
        
        fw.write(createCode(BEAN_VO_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    public void createQVO(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/main/java/" + VO_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        
        String fileName = path + getLastChar(cName) + "QVo.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        
       // bean.setBeanDaoUrl(ACTION_URL);
        //bean.setBeanDaoImplUrl(ACTION_IMPL_URL);
        
        fw.write(createCode(BEAN_QVO_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    
    public void createDAO(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/main/java/" + DAO_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        
        String fileName = path + getLastChar(cName) + "Mapper.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        
       // bean.setBeanDaoUrl(ACTION_URL);
        //bean.setBeanDaoImplUrl(ACTION_IMPL_URL);
        
        fw.write(createCode(BEAN_DAO_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    
    
    /**
     * 创建bean的Service<br>
     * 
     * @param c
     * @throws Exception
     */
    public void createService(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/main/java/" + SERVICE_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        
        String fileName = path + getLastChar(cName) + "Service.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        
        bean.setBeanServiceUrl(SERVICE_URL);
        
        fw.write(createCode(BEAN_SERVICE_TEMPLATE_VM_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    
    /**
     * 创建bean的Service的实现<br>
     * 
     * @param c
     * @throws Exception
     */
    public void createServiceImpl(Class c) throws Exception {
    		StringBuffer validate=new StringBuffer();
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/main/java/" + SERVICE_IMPL_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + getLastChar(cName) + "ServiceImpl.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        //System.out.println(SnailUtils.propertyToField(c.getSimpleName()));
        List<ColumsInfo> list=DBHelpInfo.getTableInfo(SnailUtils.propertyToField(c.getSimpleName()));
        for(ColumsInfo o:list){
        		if(o.getIsNullAble().equals("NO")){
        			validate.append("if (SnailUtils.isBlank(o.get"+SnailUtils.firstToUpper(SnailUtils.getJavaName(o.getColumName()))+"())) {\r");
                 validate.append("return new DataResponse(false, \""+o.getRemarks()+"不能为空！\");\r");
                 validate.append("}\r");
        		}
        		
        }
        
        bean.setBeanDaoUrl(ACTION_URL);
        bean.setBeanServiceUrl(SERVICE_URL);
        bean.setBeanServiceImplUrl(SERVICE_IMPL_URL);
        bean.setValidate(validate.toString());
        
        fw.write(createCode(BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 根据模板生成代码
     * @param fileVMPath 模板路径 
     * @param bean 目标bean
     * @param annotation 注释
     * @return
     * @throws Exception
     */
    public String createCode(String fileVMPath,Bean bean,Annotation annotation) throws Exception{
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(fileVMPath);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("bean", bean);
        velocityContext.put("annotation", annotation);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }
    
     /**
     * 创建文件
     * @param file
     */
    public void createFilePath(File file){
        if(!file.exists()){
            System.out.println("创建["+file.getAbsolutePath()+"]情况："+file.mkdirs());
        }else{
            System.out.println("存在目录："+file.getAbsolutePath());
        }
    }
    
 
    
    
    /**
     * 获取路径的最后面字符串<br>
     * 如：<br>
     *     <code>str = "com.b510.base.bean.User"</code><br>
     *     <code> return "User";<code>
     * @param str
     * @return
     */
    public String getLastChar(String str) {
        if ((str != null) && (str.length() > 0)) {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1))) {
                return str.substring(dot + 1);
            }
        }
        return str;
    }
    
    /**
     * 把第一个字母变为小写<br>
     * 如：<br>
     *     <code>str = "UserDao";</code><br>
     *     <code>return "userDao";</code>
     * @param str
     * @return
     */
    public String getLowercaseChar(String str){
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }

    /**
     * 显示信息
     * @param info
     */
    public void showInfo(String info){
        System.out.println("创建文件："+ info+ "成功！");
    }
    
    /**
     * 获取系统时间
     * @return
     */
    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
    
    public static String getProperty(String key) {
		ResourceBundle resource=ResourceBundle.getBundle("autocode");
		return resource.getString(key);
	}
}