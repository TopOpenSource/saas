package com.sdstc.system.dao.base;


import org.apache.ibatis.annotations.Param;
import com.sdstc.system.model.SysUrl;


public interface SysUrlBaseDao {
   void  insert(SysUrl dto);
   void  updateByPK(SysUrl dto);
   void  updateSelectiveByPK(SysUrl dto);
   void  deleteByPK(@Param("id") Long id);
   SysUrl  selectByPK(@Param("id") Long id);
   
}
