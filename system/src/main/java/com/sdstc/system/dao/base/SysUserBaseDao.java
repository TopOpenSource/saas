package com.sdstc.system.dao.base;


import org.apache.ibatis.annotations.Param;
import com.sdstc.system.model.SysUser;


public interface SysUserBaseDao {
   void  insert(SysUser dto);
   void  updateByPK(SysUser dto);
   void  updateSelectiveByPK(SysUser dto);
   void  deleteByPK(@Param("id") Long id);
   SysUser  selectByPK(@Param("id") Long id);
   
}
