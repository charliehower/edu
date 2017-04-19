package org.platform.snail.portal.web.action;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.service.QueueCmccHisService;
import org.platform.snail.portal.vo.QueueCmccHisQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/queueCmccHis")
public class QueueCmccHisAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QueueCmccHisService queueCmccHisService;
	@RequestMapping(value = "/findQueueCmccHisList.do")
	@ResponseBody
	public  DataResponse findQueueCmccHisList(QueueCmccHisQVo condition,Page page){
		try{
			DataResponse rst = this.queueCmccHisService.findQueueCmccHisList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteQueueCmccHisByQueueCmccHisId.do")
	@ResponseBody
	public  DataResponse deleteQueueCmccHisByQueueCmccHisId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.queueCmccHisService.deleteQueueCmccHisByQueueCmccHisId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
}
