package com.sdstc.system.service;

import java.util.List;
import com.sdstc.system.model.SysRole;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
/**
 * 
 * @author
 *
 */
public interface SysRoleService {
	void insert(SysRole dto);

	void updateByPK(SysRole dto);

	void updateSelectiveByPK(SysRole dto);

	void deleteByPK(Long id);

	SysRole selectByPK(Long id);

	List<SysRole> selectByDto(SysRole dto);
	
	PageResult<SysRole> selectPageByDto(SysRole dto,PageDto pageDto);
}
