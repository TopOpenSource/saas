package com.sdstc.system.dao;

import com.sdstc.system.dao.base.SysUrlBaseDao;
import com.sdstc.system.model.SysUrl;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface SysUrlDao extends SysUrlBaseDao{
   List<SysUrl> selectByDto(SysUrl dto);
   Integer selectCountByDto(SysUrl dto);
   List<SysUrl> selectPageByDto(@Param("dto")SysUrl dto,@Param("pageDto")PageDto pageDto);
}
