/**
 * @Title: TemplateServiceImpl.java
 * @Package org.platform.snail.cxf.service.impl
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年2月7日 下午6:54:14
 * @version V1.0
 */

package org.platform.snail.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.platform.snail.edu.dao.TemplateCmccMapper;
import org.platform.snail.edu.model.TemplateCmcc;
import org.platform.snail.edu.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TemplateServiceImpl
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年2月7日 下午6:54:14
 *
 */
@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateCmccMapper templateCmccMapper;
	private Map<String,String> template=new HashMap<String,String>();
	Logger logger = Logger.getLogger(this.getClass());
	public void scan() {
		this.logger.info("===========> templateService start scan");
		 List<TemplateCmcc> list=this.templateCmccMapper.selectList();
		 for(TemplateCmcc e:list){
			 this.logger.info(e.getTemplateCmccId()+"===========>"+e.getContent());
			 template.put(e.getTemplateCmccId(), e.getContent());
		 }
		 this.logger.info("===========> templateService start complete");
	}
	
	public String get(String key){
		return this.template.get(key);
	}
	

}
