package org.platform.snail.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.platform.snail.portal.model.Attach;
import org.platform.snail.portal.vo.AttachQVo;
import org.platform.snail.portal.vo.AttachVo;

public interface AttachMapper {
    int deleteByPrimaryKey(Integer attachId);

    int insert(Attach record);

    int insertSelective(Attach record);

    Attach selectByPrimaryKey(Integer attachId);

    int updateByPrimaryKeySelective(Attach record);

    int updateByPrimaryKey(Attach record);
    
    List<AttachVo> findList(@Param("condition") AttachQVo condition);
    
    int deleteByFileUrl(String fileUrl);
}