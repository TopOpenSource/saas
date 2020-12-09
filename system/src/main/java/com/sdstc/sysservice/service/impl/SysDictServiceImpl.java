package com.sdstc.sysservice.service.impl;

import com.sdstc.pub.dto.LoginUserInfo;
import com.sdstc.pub.utils.Snowflake;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdstc.sysservice.dao.SysDictDao;
import com.sdstc.sysservice.model.SysDict;
import com.sdstc.sysservice.service.SysDictService;
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
@Service("sysDictService")
@Log4j2
public class SysDictServiceImpl implements SysDictService{
    private static Snowflake snowflake = new Snowflake();

    @Autowired
	private SysDictDao sysDictDao;
    
	@Override
	public void insert(SysDict dto) {
	    Date now=DateUtils.getNow();
	    dto.setId(snowflake.nextId());
	    dto.setGmtCreate(now);
        dto.setCreateAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());
		sysDictDao.insert(dto);
	}

	@Override
	public void updateByPK(SysDict dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
        dto.setModifiedAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());

		sysDictDao.updateByPK(dto);
	}

	@Override
	public void updateSelectiveByPK(SysDict dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
        dto.setModifiedAccount(LoginUserInfo.getLoginUserInfo().getUserAccount());
		sysDictDao.updateSelectiveByPK(dto);
	}

	@Override
	public void deleteByPK(Long id,Long tenantId) {
		sysDictDao.deleteByPK(id,tenantId);
	}

	@Override
	public SysDict selectByPK(Long id,Long tenantId) {
		return sysDictDao.selectByPK(id,tenantId);
	}

	@Override
	public List<SysDict> selectByDto(SysDict dto) {
		return sysDictDao.selectByDto(dto);
	}

    @Override
	public PageResult<SysDict> selectPageByDto(SysDict dto, PageDto pageDto) {
	    pageDto.setPageCount(sysDictDao.selectCountByDto(dto));
		if(pageDto.getCount()> 0) {
			List<SysDict> results=sysDictDao.selectPageByDto(dto, pageDto);
			return new PageResult<SysDict>(pageDto, results);
		}else {
			return new PageResult<SysDict>(pageDto, null);
		}
	}
}
