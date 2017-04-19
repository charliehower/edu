package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.DwmReport;
import org.platform.snail.edu.vo.DwmReportQVo;
import org.platform.snail.edu.vo.DwmReportVo;

public interface DwmReportMapper {
    int deleteByPrimaryKey(String dwmReportId);

    int insert(DwmReport record);

    int insertSelective(DwmReport record);

    DwmReport selectByPrimaryKey(String dwmReportId);
    
    DwmReportVo selectVoByPrimaryKey(String dwmReportId);

    int updateByPrimaryKeySelective(DwmReport record);

    int updateByPrimaryKeyWithBLOBs(DwmReport record);

    int updateByPrimaryKey(DwmReport record);
    List<DwmReportVo> findList(@Param("condition") DwmReportQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DwmReportQVo condition);
	 int saveOrUpdateDwmReport(DwmReport record);
	 int isExitByTitle(@Param("title") String title);
	 int updateForTopByPrimaryKey(@Param("dwmReportId") String dwmReportId,@Param("category") String category);
	 int updateForStatusByPrimaryKey(@Param("dwmReportId") String dwmReportId,@Param("status") String status);
	 List<Map<String,String>> selectUserIdByDepartmentId(@Param("departmentId") String departmentId);
	 String selectSqlTextByGroupId(@Param("groupId") String groupId);
	 String selectDictNameByCategoryIdAndCode(@Param("categoryId") String categoryId,@Param("code") String code);
	 List<DwmReportVo> findListTop(@Param("categoryId") String categoryId,@Param("limit") int limit,@Param("userId") String userId);
	 
	 List<Map<String,Object>> getReportByCategoryIdAndTime(@Param("condition") DwmReportQVo condition);
	 
	 List<Map<String,Object>> selectUsersByObjId(String id);
	 
	 List<Map<String,Object>> selectDeptIdByUserId(String userId);
}