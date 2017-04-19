package org.platform.snail.portal.web.action;

import org.apache.log4j.Logger;
import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.Page;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.service.AttachService;
import org.platform.snail.portal.vo.AttachQVo;
import org.platform.snail.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/attach")
public class AttachAction extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AttachService attachService;
	@RequestMapping(value = "/findAttachList.do")
	@ResponseBody
	public  DataResponse findAttachList(AttachQVo condition,Page page){
		try{
			DataResponse rst = this.attachService.findAttachList(condition);
			if (rst.getAllRows() == null) {
				rst.setAllRows(page.getTotalRecord());
			}
			return rst;
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteAttachByFileName.do")
	@ResponseBody
	public  DataResponse deleteAttachByFileName(String fileName){
		try{
			SystemUser systemUser =this.getSessionSystemUser();
			return this.attachService.deleteAttachByFileName(fileName,systemUser);
		}catch(Exception e){
			this.logger.error(e);
			return new DataResponse(false,"删除失败！",e.getMessage());
		}
	}
	@RequestMapping(value = "/uploadFile.do")
	@ResponseBody
	public DataResponse uploadFile(@RequestParam MultipartFile[] file,String noticeId, String collectionName)
			throws Exception {
		try {
			return this.attachService.upload(file,noticeId, collectionName, this.getSessionSystemUser());
		} catch (Exception e) {
			this.logger.error(e);
			return new DataResponse(false,"上传失败",e.getMessage());
		}
	}
}
