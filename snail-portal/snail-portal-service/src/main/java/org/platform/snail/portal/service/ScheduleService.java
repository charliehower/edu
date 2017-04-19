package org.platform.snail.portal.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.platform.snail.beans.DataResponse;
import org.platform.snail.beans.SystemUser;
import org.platform.snail.portal.vo.ScheduleQVo;

public interface ScheduleService {
	public abstract DataResponse findScheduleList(ScheduleQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract DataResponse insertSchedule(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse updateSchedule(JSONObject json,SystemUser systemUser) throws Exception;
	public abstract DataResponse selectScheduleByPrimaryKey(String id) throws Exception;
	public abstract DataResponse deleteScheduleByScheduleId(String id,SystemUser systemUser) throws Exception;
	
	public abstract List<Map<String,Object>> selectDepUserListByDepId(String id);

}
