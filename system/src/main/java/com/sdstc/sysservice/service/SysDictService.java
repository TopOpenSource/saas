package com.sdstc.sysservice.service;

import java.util.List;
import com.sdstc.sysservice.model.SysDict;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
/**
 * 
 * @author
 *
 */
public interface SysDictService {
	void insert(SysDict dto);

	void updateByPK(SysDict dto);

	void updateSelectiveByPK(SysDict dto);

	void deleteByPK(Long id,Long tenantId);

	SysDict selectByPK(Long id,Long tenantId);

	List<SysDict> selectByDto(SysDict dto);
	
	PageResult<SysDict> selectPageByDto(SysDict dto,PageDto pageDto);
}
