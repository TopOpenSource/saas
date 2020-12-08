package com.sdstc.system.service;

import java.util.List;
import com.sdstc.system.model.SysUser;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
/**
 * 
 * @author
 *
 */
public interface SysUserService {
	void insert(SysUser dto);

	void updateByPK(SysUser dto);

	void updateSelectiveByPK(SysUser dto);

	void deleteByPK(Long id);

	SysUser selectByPK(Long id);

	List<SysUser> selectByDto(SysUser dto);
	
	PageResult<SysUser> selectPageByDto(SysUser dto,PageDto pageDto);
}
