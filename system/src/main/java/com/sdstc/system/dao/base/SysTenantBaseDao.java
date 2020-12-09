package com.sdstc.system.dao.base;


import org.apache.ibatis.annotations.Param;
import com.sdstc.system.model.SysTenant;


public interface SysTenantBaseDao {
   void  insert(SysTenant dto);
   void  updateByPK(SysTenant dto);
   void  updateSelectiveByPK(SysTenant dto);
   void  deleteByPK(@Param("id") Long id);
   SysTenant  selectByPK(@Param("id") Long id);
   
}
