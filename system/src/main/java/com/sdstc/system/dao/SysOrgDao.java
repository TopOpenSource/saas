package com.sdstc.system.dao;

import com.sdstc.system.dao.base.SysOrgBaseDao;
import com.sdstc.system.model.SysOrg;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface SysOrgDao extends SysOrgBaseDao{
   List<SysOrg> selectByDto(SysOrg dto);
   Integer selectCountByDto(SysOrg dto);
   List<SysOrg> selectPageByDto(@Param("dto")SysOrg dto,@Param("pageDto")PageDto pageDto);
}
