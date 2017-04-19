package org.platform.snail.web.action;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/sysConfig")
public class SysConfigAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SystemService systemService;
	

	@RequestMapping(value = "/deployConfig.do")
	@ResponseBody
	public  DataResponse deployConfig(){
		try{
			this.systemService.loadConfig(this.getRequest().getSession().getServletContext());
			return new DataResponse(true,"发布成功！");
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"发布失败！",e.getMessage());
		}
	}
}
