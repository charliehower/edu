/**
 * @Title: MongoService.java
 * @Package org.platform.snail.service
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月20日 上午8:26:33
 * @version V1.0
 */

package org.platform.snail.portal.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

/**
 * @ClassName: MongoService
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月20日 上午8:26:33
 *
 */

public interface MongoService {
	public boolean savefile( String collectionName, File file, String fileId,String fileName)throws IOException;
	public boolean savefile(String collectionName, InputStream in, String fileId,
			String fileName) throws IOException;
	public GridFSDBFile retrieveFileOne(String collectionName, String filenName)throws IOException ;
	
	public abstract DataResponse upload(MultipartFile[] file, String collectionName,SystemUser systemUser) throws IOException;
}
