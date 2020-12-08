package com.sdstc.system.dao;

import com.sdstc.system.dao.base.SysPermBaseDao;
import com.sdstc.system.model.SysPerm;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface SysPermDao extends SysPermBaseDao{
   List<SysPerm> selectByDto(SysPerm dto);
   Integer selectCountByDto(SysPerm dto);
   List<SysPerm> selectPageByDto(@Param("dto")SysPerm dto,@Param("pageDto")PageDto pageDto);
}
