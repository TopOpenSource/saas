package com.sdstc.system.dao;

import com.sdstc.system.dao.base.SysRoleBaseDao;
import com.sdstc.system.model.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface SysRoleDao extends SysRoleBaseDao{
   List<SysRole> selectByDto(SysRole dto);
   Integer selectCountByDto(SysRole dto);
   List<SysRole> selectPageByDto(@Param("dto")SysRole dto,@Param("pageDto")PageDto pageDto);
}
