package com.sdstc.system.service.impl;

import com.sdstc.pub.utils.Snowflake;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdstc.system.dao.SysOrgDao;
import com.sdstc.system.model.SysOrg;
import com.sdstc.system.service.SysOrgService;
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
@Service("sysOrgService")
@Log4j2
public class SysOrgServiceImpl implements SysOrgService{
    private static Snowflake snowflake = new Snowflake();

    @Autowired
	private SysOrgDao sysOrgDao;
    
	@Override
	public void insert(SysOrg dto) {
	    Date now=DateUtils.getNow();
	    dto.setId(snowflake.nextId());
	    dto.setGmtCreate(now);
	    
		sysOrgDao.insert(dto);
	}

	@Override
	public void updateByPK(SysOrg dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		sysOrgDao.updateByPK(dto);
	}

	@Override
	public void updateSelectiveByPK(SysOrg dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		sysOrgDao.updateSelectiveByPK(dto);
	}

	@Override
	public void deleteByPK(Long id) {
		sysOrgDao.deleteByPK(id);
	}

	@Override
	public SysOrg selectByPK(Long id) {
		return sysOrgDao.selectByPK(id);
	}

	@Override
	public List<SysOrg> selectByDto(SysOrg dto) {
		return sysOrgDao.selectByDto(dto);
	}

    @Override
	public PageResult<SysOrg> selectPageByDto(SysOrg dto, PageDto pageDto) {
	    pageDto.setPageCount(sysOrgDao.selectCountByDto(dto));
		if(pageDto.getCount()> 0) {
			List<SysOrg> results=sysOrgDao.selectPageByDto(dto, pageDto);
			return new PageResult<SysOrg>(pageDto, results);
		}else {
			return new PageResult<SysOrg>(pageDto, null);
		}
	}
}
