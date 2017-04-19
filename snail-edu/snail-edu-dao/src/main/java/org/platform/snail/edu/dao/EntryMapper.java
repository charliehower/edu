package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Entry;
import org.platform.snail.edu.vo.EntryQVo;
import org.platform.snail.edu.vo.EntryVo;

public interface EntryMapper {
    int deleteByPrimaryKey(String teacherId);

    int insert(Entry record);

    int insertSelective(Entry record);

    Entry selectByPrimaryKey(String teacherId);

    int updateByPrimaryKeySelective(Entry record);

    int updateByPrimaryKey(Entry record);
    
    int saveOrUpdateEntry(Entry record);
    
    List<EntryVo> findList(@Param("condition") EntryQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EntryQVo condition);
}