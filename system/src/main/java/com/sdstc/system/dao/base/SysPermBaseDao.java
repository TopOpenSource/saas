package com.sdstc.system.dao.base;


import org.apache.ibatis.annotations.Param;
import com.sdstc.system.model.SysPerm;


public interface SysPermBaseDao {
   void  insert(SysPerm dto);
   void  updateByPK(SysPerm dto);
   void  updateSelectiveByPK(SysPerm dto);
   void  deleteByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   SysPerm  selectByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   
}
