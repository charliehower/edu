package org.platform.snail.portal.web.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.portal.model.Dict;
import org.platform.snail.service.DataBaseLogService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/logs")
public class LogsAction extends BaseController{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@RequestMapping(value = "/findList.do")
	@ResponseBody
	public DataResponse findDictList(HttpServletRequest request,Page page){
		try{
			Map<String, Object> condition = new HashMap<String, Object>();  
			Enumeration e=request.getParameterNames();
			while(e.hasMoreElements()){
				String key=(String)e.nextElement();
				String value=request.getParameter(key);
				condition.put(key, value);
			}
			DataResponse rst = this.dataBaseLogService.findList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
		
	}
	
	
}
