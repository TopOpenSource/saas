package com.sdstc.system.service.impl;

import com.sdstc.pub.utils.Snowflake;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdstc.system.dao.SysUrlDao;
import com.sdstc.system.model.SysUrl;
import com.sdstc.system.service.SysUrlService;
import com.sdstc.pub.dto.PageDto;
import com.sdstc.pub.dto.PageResult;
import com.sdstc.pub.utils.DateUtils;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
/**
 * 
 * @author 
 *
 */
@Service("sysUrlService")
@Log4j2
public class SysUrlServiceImpl implements SysUrlService{
    private static Snowflake snowflake = new Snowflake();

    @Autowired
	private SysUrlDao sysUrlDao;
    
	@Override
	public void insert(SysUrl dto) {
	    Date now=DateUtils.getNow();
	    dto.setId(snowflake.nextId());
	    dto.setGmtCreate(now);
	    
		sysUrlDao.insert(dto);
	}

	@Override
	public void updateByPK(SysUrl dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		sysUrlDao.updateByPK(dto);
	}

	@Override
	public void updateSelectiveByPK(SysUrl dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		sysUrlDao.updateSelectiveByPK(dto);
	}

	@Override
	public void deleteByPK(Long id) {
		sysUrlDao.deleteByPK(id);
	}

	@Override
	public SysUrl selectByPK(Long id) {
		return sysUrlDao.selectByPK(id);
	}

	@Override
	public List<SysUrl> selectByDto(SysUrl dto) {
		return sysUrlDao.selectByDto(dto);
	}

    @Override
	public PageResult<SysUrl> selectPageByDto(SysUrl dto, PageDto pageDto) {
	    pageDto.setPageCount(sysUrlDao.selectCountByDto(dto));
		if(pageDto.getCount()> 0) {
			List<SysUrl> results=sysUrlDao.selectPageByDto(dto, pageDto);
			return new PageResult<SysUrl>(pageDto, results);
		}else {
			return new PageResult<SysUrl>(pageDto, null);
		}
	}
}
