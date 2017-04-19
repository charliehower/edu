package org.platform.snail.portal.service;

import java.io.IOException;
import java.io.OutputStream;

import jxl.write.WriteException;
import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.model.ExcelMain;
import org.platform.snail.portal.vo.ExcelMainQVo;
import org.springframework.web.multipart.MultipartFile;
public interface ExcelMainService {
	public abstract DataResponse findExcelMainList(ExcelMainQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertExcelMain(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateExcelMain(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectExcelMainByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteExcelMainByExcelMainId(String id,SystemUser systemUser) throws Exception;
    public DataResponse importXls(MultipartFile[] files,JSONObject json,SystemUser systemUser) throws Exception;
    public void exportXls(OutputStream os, String id,boolean exportALl,
			SystemUser systemUser) throws WriteException, IOException ;
}
