package ${servicePackage};

import com.sdstc.pub.utils.Snowflake;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${customDaoPackage}.${entityName}Dao;
import ${modelPackage}.${entityName};
import ${serviceInterPackage}.${entityName}Service;
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
@Service("${entityNameLowerCase}Service")
@Log4j2
public class ${entityName}ServiceImpl implements ${entityName}Service{
    private static Snowflake snowflake = new Snowflake();

    @Autowired
	private ${entityName}Dao ${entityNameLowerCase}Dao;
    
	@Override
	public void insert(${entityName} dto) {
	    Date now=DateUtils.getNow();
	    dto.setId(snowflake.nextId());
	    dto.setGmtCreate(now);
	    
		${entityNameLowerCase}Dao.insert(dto);
	}

	@Override
	public void updateByPK(${entityName} dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		${entityNameLowerCase}Dao.updateByPK(dto);
	}

	@Override
	public void updateSelectiveByPK(${entityName} dto) {
	    Date now=DateUtils.getNow();
		dto.setGmtModified(now);
		
		${entityNameLowerCase}Dao.updateSelectiveByPK(dto);
	}

	@Override
	public void deleteByPK(${pkJavaType} ${primaryKey}<#if hasTenant?string("true","false")== "true">,${tenantKeyJavaType} ${tenantKey}</#if>) {
		${entityNameLowerCase}Dao.deleteByPK(${primaryKey}<#if hasTenant?string("true","false")== "true">,${tenantKey}</#if>);
	}

	@Override
	public ${entityName} selectByPK(${pkJavaType} ${primaryKey}<#if hasTenant?string("true","false")== "true">,${tenantKeyJavaType} ${tenantKey}</#if>) {
		return ${entityNameLowerCase}Dao.selectByPK(${primaryKey}<#if hasTenant?string("true","false")== "true">,${tenantKey}</#if>);
	}

	@Override
	public List<${entityName}> selectByDto(${entityName} dto) {
		return ${entityNameLowerCase}Dao.selectByDto(dto);
	}

    @Override
	public PageResult<${entityName}> selectPageByDto(${entityName} dto, PageDto pageDto) {
	    pageDto.setPageCount(${entityNameLowerCase}Dao.selectCountByDto(dto));
		if(pageDto.getCount()> 0) {
			List<${entityName}> results=${entityNameLowerCase}Dao.selectPageByDto(dto, pageDto);
			return new PageResult<${entityName}>(pageDto, results);
		}else {
			return new PageResult<${entityName}>(pageDto, null);
		}
	}
}
