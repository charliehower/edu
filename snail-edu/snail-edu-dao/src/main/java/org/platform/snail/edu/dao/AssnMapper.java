package org.platform.snail.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.edu.model.Assn;
import org.platform.snail.edu.model.AssnSub;
import org.platform.snail.edu.vo.AssnQVo;
import org.platform.snail.edu.vo.AssnVo;

public interface AssnMapper {
    int deleteByPrimaryKey(String assnId);

    int insert(Assn record);

    int insertSelective(Assn record);

    AssnVo selectByPrimaryKey(String assnId);

    int updateByPrimaryKeySelective(Assn record);

    int updateByPrimaryKeyWithBLOBs(Assn record);

    int updateByPrimaryKey(Assn record);
    
    List<AssnVo> findList(@Param("condition") AssnQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") AssnQVo condition);

	int isExitByRecord(Assn record);
	
	int insertAssnSub(Map<String,Object> record);
	int insertAssnSubReg(AssnSub record);
	
	int isReg(AssnSub record);
	
	int isLoadMaxLimit(String assnId);
	
	List<Map<String,Object>> selectAssnSubByAssnId(String assnId);

	int deleteSubByAssnId(@Param("assnId")String assnId,@Param("categoryId")String categoryId);
	
	List<Map<String,Object>> selectAssnCategory(String id);
}