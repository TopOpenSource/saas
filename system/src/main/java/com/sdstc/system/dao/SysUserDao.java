package com.sdstc.system.dao;

import com.sdstc.system.dao.base.SysUserBaseDao;
import com.sdstc.system.model.SysUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface SysUserDao extends SysUserBaseDao{
   List<SysUser> selectByDto(SysUser dto);
   Integer selectCountByDto(SysUser dto);
   List<SysUser> selectPageByDto(@Param("dto")SysUser dto,@Param("pageDto")PageDto pageDto);
}
