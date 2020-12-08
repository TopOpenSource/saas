package ${customDaoPackage};

import ${baseDaoPackage}.${entityName}BaseDao;
import ${modelPackage}.${entityName};
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sdstc.pub.dto.PageDto;

public interface ${entityName}Dao extends ${entityName}BaseDao{
   List<${entityName}> selectByDto(${entityName} dto);
   Integer selectCountByDto(${entityName} dto);
   List<${entityName}> selectPageByDto(@Param("dto")${entityName} dto,@Param("pageDto")PageDto pageDto);
}
