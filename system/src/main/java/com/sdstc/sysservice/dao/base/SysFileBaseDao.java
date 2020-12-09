package com.sdstc.sysservice.dao.base;


import org.apache.ibatis.annotations.Param;
import com.sdstc.sysservice.model.SysFile;


public interface SysFileBaseDao {
   void  insert(SysFile dto);
   void  updateByPK(SysFile dto);
   void  updateSelectiveByPK(SysFile dto);
   void  deleteByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   SysFile  selectByPK(@Param("id") Long id,@Param("tenantId") Long tenantId);
   
}
