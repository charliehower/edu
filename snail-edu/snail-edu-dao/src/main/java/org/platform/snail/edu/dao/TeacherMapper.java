package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Teacher;
import org.platform.snail.edu.vo.TeacherQVo;
import org.platform.snail.edu.vo.TeacherVo;

public interface TeacherMapper {
    int deleteByPrimaryKey(String teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String teacherId);
    
    

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKeyWithBLOBs(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    int updateGroupByPrimaryKey(Teacher record);
    
    List<TeacherVo> findList(@Param("condition") TeacherQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TeacherQVo condition);

	int isExitByIdCard(@Param("idCard") String idCard);
	int isExitByIdTeacherId(@Param("teacherId") String teacherId);
	
	int saveOrUpdateUsersByTeacherId(@Param("teacherId") String teacherId);
	int updateQuitByIdTeacherId(@Param("teacherId") String teacherId);
	
	List<Map<String,String>> getDictList();
}