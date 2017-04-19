package org.platform.snail.portal.web.action;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.service.QueueCmccWaitService;
import org.platform.snail.portal.vo.QueueCmccWaitQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/queueCmccWait")
public class QueueCmccWaitAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QueueCmccWaitService queueCmccWaitService;
	@RequestMapping(value = "/findQueueCmccWaitList.do")
	@ResponseBody
	public  DataResponse findQueueCmccWaitList(QueueCmccWaitQVo condition,Page page){
		try{
			DataResponse rst = this.queueCmccWaitService.findQueueCmccWaitList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteQueueCmccWaitByQueueCmccWaitId.do")
	@ResponseBody
	public  DataResponse deleteQueueCmccWaitByQueueCmccWaitId(String id){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.queueCmccWaitService.deleteQueueCmccWaitByQueueCmccWaitId(id,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
}
