package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Student;
import org.platform.snail.edu.vo.StudentQVo;
import org.platform.snail.edu.vo.StudentVo;

public interface StudentMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    List<StudentVo> findList(@Param("condition") StudentQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") StudentQVo condition);

	int isExitByRecord(Student record);
}