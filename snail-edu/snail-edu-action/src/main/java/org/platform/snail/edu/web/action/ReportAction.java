package org.platform.snail.edu.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.edu.service.ReportService;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/report")
public class ReportAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/query.do")
	@ResponseBody
	public DataResponse query(String reportId, HttpServletRequest request) {
		try {
			DataResponse rst = this.reportService.query(
					this.getParamsMap(request), reportId);
			return rst;
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false, e.getMessage());
		}
	}

}
