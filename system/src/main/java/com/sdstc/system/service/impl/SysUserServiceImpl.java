package com.sdstc.system.service.impl;

import java.util.List;

import com.sdstc.pub.utils.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdstc.system.dao.SysUserDao;
import com.sdstc.system.model.SysUser;
import com.sdstc.system.service.SysUserService;
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
@Service("sysUserService")
@Log4j2
public class SysUserServiceImpl implements SysUserService{
	private static Snowflake snowflake = new Snowflake();

    @Autowired
	private SysUserDao sysUserDao;
    
	@Override
	public void insert(SysUser dto) {
	    Date now=DateUtils.getNow();
	    dto.setId(snowflake.nextId());
	    dto.setGmtCreate(now);
	    
		sysUserDao.insert(dto);
	}

	@Override
	public void updateByPK(SysUser dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		sysUserDao.updateByPK(dto);
	}

	@Override
	public void updateSelectiveByPK(SysUser dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		sysUserDao.updateSelectiveByPK(dto);
	}

	@Override
	public void deleteByPK(Long id) {
		sysUserDao.deleteByPK(id);
	}

	@Override
	public SysUser selectByPK(Long id) {
		return sysUserDao.selectByPK(id);
	}

	@Override
	public List<SysUser> selectByDto(SysUser dto) {
		return sysUserDao.selectByDto(dto);
	}

    @Override
	public PageResult<SysUser> selectPageByDto(SysUser dto, PageDto pageDto) {
	    pageDto.setPageCount(sysUserDao.selectCountByDto(dto));
		if(pageDto.getCount()> 0) {
			List<SysUser> results=sysUserDao.selectPageByDto(dto, pageDto);
			return new PageResult<SysUser>(pageDto, results);
		}else {
			return new PageResult<SysUser>(pageDto, null);
		}
	}
}
