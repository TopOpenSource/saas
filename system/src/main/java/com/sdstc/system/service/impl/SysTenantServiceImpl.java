package com.sdstc.system.service.impl;

import com.sdstc.pub.dto.LoginUserInfo;
import com.sdstc.pub.utils.Snowflake;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdstc.system.dao.SysTenantDao;
import com.sdstc.system.model.SysTenant;
import com.sdstc.system.service.SysTenantService;
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
@Service("sysTenantService")
@Log4j2
public class SysTenantServiceImpl implements SysTenantService{
    private static Snowflake snowflake = new Snowflake();

    @Autowired
	private SysTenantDao sysTenantDao;
    
	@Override
	public void insert(SysTenant dto) {
	    Date now=DateUtils.getNow();
	    dto.setId(snowflake.nextId());
	    dto.setGmtCreate(now);
        dto.setCreateAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());
		sysTenantDao.insert(dto);
	}

	@Override
	public void updateByPK(SysTenant dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
        dto.setModifiedAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());

		sysTenantDao.updateByPK(dto);
	}

	@Override
	public void updateSelectiveByPK(SysTenant dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
        dto.setModifiedAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());
		sysTenantDao.updateSelectiveByPK(dto);
	}

	@Override
	public void deleteByPK(Long id) {
		sysTenantDao.deleteByPK(id);
	}

	@Override
	public SysTenant selectByPK(Long id) {
		return sysTenantDao.selectByPK(id);
	}

	@Override
	public List<SysTenant> selectByDto(SysTenant dto) {
		return sysTenantDao.selectByDto(dto);
	}

    @Override
	public PageResult<SysTenant> selectPageByDto(SysTenant dto, PageDto pageDto) {
	    pageDto.setPageCount(sysTenantDao.selectCountByDto(dto));
		if(pageDto.getCount()> 0) {
			List<SysTenant> results=sysTenantDao.selectPageByDto(dto, pageDto);
			return new PageResult<SysTenant>(pageDto, results);
		}else {
			return new PageResult<SysTenant>(pageDto, null);
		}
	}
}
