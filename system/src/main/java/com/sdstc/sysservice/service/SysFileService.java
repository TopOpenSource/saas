package com.sdstc.sysservice.service;

import java.util.List;
import com.sdstc.sysservice.model.SysFile;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
/**
 * 
 * @author
 *
 */
public interface SysFileService {
	void insert(SysFile dto);

	void updateByPK(SysFile dto);

	void updateSelectiveByPK(SysFile dto);

	void deleteByPK(Long id,Long tenantId);

	SysFile selectByPK(Long id,Long tenantId);

	List<SysFile> selectByDto(SysFile dto);
	
	PageResult<SysFile> selectPageByDto(SysFile dto,PageDto pageDto);
}
