package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.SiteApp;
import org.platform.snail.edu.vo.SiteAppQVo;
import org.platform.snail.edu.vo.SiteAppVo;

public interface SiteAppMapper {
    int deleteByPrimaryKey(String id);

    int insert(SiteApp record);

    int insertSelective(SiteApp record);

    SiteApp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SiteApp record);

    int updateByPrimaryKey(SiteApp record);
    
    List<SiteAppVo> findList(@Param("condition") SiteAppQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") SiteAppQVo condition);

	int isExit(SiteApp record);
	
	List<Map<String,String>> selectSiteList(String pid);
	List<Map<String,Object>> selectListByStart(@Param("start")String start,@Param("id")String id);
}