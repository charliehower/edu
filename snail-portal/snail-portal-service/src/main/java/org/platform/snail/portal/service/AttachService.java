package org.platform.snail.portal.service;

import java.io.IOException;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.vo.AttachQVo;
import org.springframework.web.multipart.MultipartFile;

public interface AttachService {
	public abstract DataResponse findAttachList(AttachQVo condition) throws Exception;
	public abstract DataResponse deleteAttachByFileName(String id,SystemUser systemUser) throws Exception;
	public abstract DataResponse upload(MultipartFile[] file,String noticeId,String collectionName,SystemUser systemUser) throws IOException;


}
