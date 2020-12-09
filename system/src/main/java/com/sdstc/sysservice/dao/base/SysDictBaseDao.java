package com.sdstc.sysservice.dao.base;


import org.apache.ibatis.annotations.Param;
import com.sdstc.sysservice.model.SysDict;


public interface SysDictBaseDao {
   void  insert(SysDict dto);
   void  updateByPK(SysDict dto);
   void  updateSelectiveByPK(SysDict dto);
   void  deleteByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   SysDict  selectByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   
}
