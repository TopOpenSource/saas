package com.sdstc.system.dao.base;


import org.apache.ibatis.annotations.Param;
import com.sdstc.system.model.SysRole;


public interface SysRoleBaseDao {
   void  insert(SysRole dto);
   void  updateByPK(SysRole dto);
   void  updateSelectiveByPK(SysRole dto);
   void  deleteByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   SysRole  selectByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   
}
