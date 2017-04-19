package org.platform.snail.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Site;
import org.platform.snail.edu.vo.SiteQVo;
import org.platform.snail.edu.vo.SiteVo;

public interface SiteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);
    
    List<SiteVo> findList(@Param("condition") SiteQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") SiteQVo condition);

	int isExit(Site record);
}