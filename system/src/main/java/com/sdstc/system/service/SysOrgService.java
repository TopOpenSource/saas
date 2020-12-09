package com.sdstc.system.service;

import java.util.List;
import com.sdstc.system.model.SysOrg;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
/**
 * 
 * @author
 *
 */
public interface SysOrgService {
	void insert(SysOrg dto);

	void updateByPK(SysOrg dto);

	void updateSelectiveByPK(SysOrg dto);

	void deleteByPK(Long id,Long tenantId);

	SysOrg selectByPK(Long id,Long tenantId);

	List<SysOrg> selectByDto(SysOrg dto);
	
	PageResult<SysOrg> selectPageByDto(SysOrg dto,PageDto pageDto);
}
