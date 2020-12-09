package com.sdstc.system.service;

import java.util.List;
import com.sdstc.system.model.SysTenant;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
/**
 * 
 * @author
 *
 */
public interface SysTenantService {
	void insert(SysTenant dto);

	void updateByPK(SysTenant dto);

	void updateSelectiveByPK(SysTenant dto);

	void deleteByPK(Long id);

	SysTenant selectByPK(Long id);

	List<SysTenant> selectByDto(SysTenant dto);
	
	PageResult<SysTenant> selectPageByDto(SysTenant dto,PageDto pageDto);
}
