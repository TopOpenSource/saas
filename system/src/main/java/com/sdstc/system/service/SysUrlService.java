package com.sdstc.system.service;

import java.util.List;
import com.sdstc.system.model.SysUrl;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
/**
 * 
 * @author
 *
 */
public interface SysUrlService {
	void insert(SysUrl dto);

	void updateByPK(SysUrl dto);

	void updateSelectiveByPK(SysUrl dto);

	void deleteByPK(Long id);

	SysUrl selectByPK(Long id);

	List<SysUrl> selectByDto(SysUrl dto);
	
	PageResult<SysUrl> selectPageByDto(SysUrl dto,PageDto pageDto);
}
