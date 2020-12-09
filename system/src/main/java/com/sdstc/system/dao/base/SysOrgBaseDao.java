package com.sdstc.system.dao.base;


import org.apache.ibatis.annotations.Param;
import com.sdstc.system.model.SysOrg;


public interface SysOrgBaseDao {
   void  insert(SysOrg dto);
   void  updateByPK(SysOrg dto);
   void  updateSelectiveByPK(SysOrg dto);
   void  deleteByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   SysOrg  selectByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   
}
