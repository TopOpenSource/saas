package com.sdstc.system.service.impl;

import com.sdstc.pub.utils.Snowflake;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdstc.system.dao.SysPermDao;
import com.sdstc.system.model.SysPerm;
import com.sdstc.system.service.SysPermService;
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
@Service("sysPermService")
@Log4j2
public class SysPermServiceImpl implements SysPermService{
    private static Snowflake snowflake = new Snowflake();

    @Autowired
	private SysPermDao sysPermDao;
    
	@Override
	public void insert(SysPerm dto) {
	    Date now=DateUtils.getNow();
	    dto.setId(snowflake.nextId());
	    dto.setGmtCreate(now);
	    
		sysPermDao.insert(dto);
	}

	@Override
	public void updateByPK(SysPerm dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		sysPermDao.updateByPK(dto);
	}

	@Override
	public void updateSelectiveByPK(SysPerm dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		sysPermDao.updateSelectiveByPK(dto);
	}

	@Override
	public void deleteByPK(Long id) {
		sysPermDao.deleteByPK(id);
	}

	@Override
	public SysPerm selectByPK(Long id) {
		return sysPermDao.selectByPK(id);
	}

	@Override
	public List<SysPerm> selectByDto(SysPerm dto) {
		return sysPermDao.selectByDto(dto);
	}

    @Override
	public PageResult<SysPerm> selectPageByDto(SysPerm dto, PageDto pageDto) {
	    pageDto.setPageCount(sysPermDao.selectCountByDto(dto));
		if(pageDto.getCount()> 0) {
			List<SysPerm> results=sysPermDao.selectPageByDto(dto, pageDto);
			return new PageResult<SysPerm>(pageDto, results);
		}else {
			return new PageResult<SysPerm>(pageDto, null);
		}
	}
}
