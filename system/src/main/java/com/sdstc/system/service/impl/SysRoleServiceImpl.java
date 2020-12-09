package com.sdstc.system.service.impl;

import com.sdstc.pub.dto.LoginUserInfo;
import com.sdstc.pub.utils.Snowflake;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdstc.system.dao.SysRoleDao;
import com.sdstc.system.model.SysRole;
import com.sdstc.system.service.SysRoleService;
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
@Service("sysRoleService")
@Log4j2
public class SysRoleServiceImpl implements SysRoleService{
    private static Snowflake snowflake = new Snowflake();

    @Autowired
	private SysRoleDao sysRoleDao;
    
	@Override
	public void insert(SysRole dto) {
	    Date now=DateUtils.getNow();
	    dto.setId(snowflake.nextId());
	    dto.setGmtCreate(now);
        dto.setCreateAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());
		sysRoleDao.insert(dto);
	}

	@Override
	public void updateByPK(SysRole dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
        dto.setModifiedAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());

		sysRoleDao.updateByPK(dto);
	}

	@Override
	public void updateSelectiveByPK(SysRole dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
        dto.setModifiedAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());
		sysRoleDao.updateSelectiveByPK(dto);
	}

	@Override
	public void deleteByPK(Long id,Long tenantId) {
		sysRoleDao.deleteByPK(id,tenantId);
	}

	@Override
	public SysRole selectByPK(Long id,Long tenantId) {
		return sysRoleDao.selectByPK(id,tenantId);
	}

	@Override
	public List<SysRole> selectByDto(SysRole dto) {
		return sysRoleDao.selectByDto(dto);
	}

    @Override
	public PageResult<SysRole> selectPageByDto(SysRole dto, PageDto pageDto) {
	    pageDto.setPageCount(sysRoleDao.selectCountByDto(dto));
		if(pageDto.getCount()> 0) {
			List<SysRole> results=sysRoleDao.selectPageByDto(dto, pageDto);
			return new PageResult<SysRole>(pageDto, results);
		}else {
			return new PageResult<SysRole>(pageDto, null);
		}
	}
}
