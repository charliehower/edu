package org.platform.snail.edu.web.action;

import java.util.Date;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.edu.service.DutyDetailService;
import org.platform.snail.edu.vo.DutyDetailQVo;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/dutyDetail")
public class DutyDetailAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DutyDetailService dutyDetailService;
	@RequestMapping(value = "/findDutyDetailList.do")
	@ResponseBody
	public  DataResponse findDutyDetailList(DutyDetailQVo condition,Page page){
		try{
			DataResponse rst = this.dutyDetailService.findDutyDetailList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	@RequestMapping(value = "/findDutyDetailListToday.do")
	@ResponseBody
	public  DataResponse findDutyDetailListToday(){
		try{
			DutyDetailQVo condition=new DutyDetailQVo();
			condition.setDateTime(SnailUtils.parseDate(new Date()));
			DataResponse rst = this.dutyDetailService.findDutyDetailList(condition,0, 50, null);
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
}
