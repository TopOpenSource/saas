package com.sdstc.system.service;

import java.util.List;
import com.sdstc.system.model.SysPerm;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
/**
 * 
 * @author
 *
 */
public interface SysPermService {
	void insert(SysPerm dto);

	void updateByPK(SysPerm dto);

	void updateSelectiveByPK(SysPerm dto);

	void deleteByPK(Long id,Long tenantId);

	SysPerm selectByPK(Long id,Long tenantId);

	List<SysPerm> selectByDto(SysPerm dto);
	
	PageResult<SysPerm> selectPageByDto(SysPerm dto,PageDto pageDto);
}
