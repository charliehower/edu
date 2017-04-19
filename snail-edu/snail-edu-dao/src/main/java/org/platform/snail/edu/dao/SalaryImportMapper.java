package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.SalaryImport;
import org.platform.snail.edu.vo.SalaryImportQVo;
import org.platform.snail.edu.vo.SalaryImportVo;

public interface SalaryImportMapper {


	int deleteByPrimaryKey(String salaryImportId);

    int insert(SalaryImport record);

    int insertSelective(SalaryImport record);

    SalaryImport selectByPrimaryKey(String salaryImportId);

    int updateByPrimaryKeySelective(SalaryImport record);

    int updateByPrimaryKey(SalaryImport record);
    
    List<SalaryImportVo> findList(@Param("condition") SalaryImportQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") SalaryImportQVo condition);
	
	int isExitBySalaryImportId(String salaryImportId);
	
	 List<Map<String,Object>> findListAll(@Param("condition")Map<String,Object> condition);
	 
	 int updateTeacherCategoryByNameList(String salaryImportId);
}