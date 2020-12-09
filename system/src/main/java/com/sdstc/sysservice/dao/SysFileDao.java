package com.sdstc.sysservice.dao;

import com.sdstc.sysservice.dao.base.SysFileBaseDao;
import com.sdstc.sysservice.model.SysFile;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface SysFileDao extends SysFileBaseDao{
   List<SysFile> selectByDto(SysFile dto);
   Integer selectCountByDto(SysFile dto);
   List<SysFile> selectPageByDto(@Param("dto")SysFile dto,@Param("pageDto")PageDto pageDto);
}
