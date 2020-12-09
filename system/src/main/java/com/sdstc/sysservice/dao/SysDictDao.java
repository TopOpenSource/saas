package com.sdstc.sysservice.dao;

import com.sdstc.sysservice.dao.base.SysDictBaseDao;
import com.sdstc.sysservice.model.SysDict;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface SysDictDao extends SysDictBaseDao{
   List<SysDict> selectByDto(SysDict dto);
   Integer selectCountByDto(SysDict dto);
   List<SysDict> selectPageByDto(@Param("dto")SysDict dto,@Param("pageDto")PageDto pageDto);
}
