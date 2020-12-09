package com.sdstc.system.dao;

import com.sdstc.system.dao.base.SysTenantBaseDao;
import com.sdstc.system.model.SysTenant;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface SysTenantDao extends SysTenantBaseDao{
   List<SysTenant> selectByDto(SysTenant dto);
   Integer selectCountByDto(SysTenant dto);
   List<SysTenant> selectPageByDto(@Param("dto")SysTenant dto,@Param("pageDto")PageDto pageDto);
}
