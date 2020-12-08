package ${modelPackage};

import com.sdstc.pub.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#if hasDate?string("true","false")== "true">
import java.util.Date;
</#if>
<#if hasBigDecimal?string("true","false")== "true">
import java.math.BigDecimal;
</#if>

/**
 * @author system
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ${entityName} extends BaseModel {
<#list cols as col>
 <#if col.isParentCol?string("true","false")== "false">
    /**
	 * ${col.columnComment}
	 */
    private ${col.javaDataType} ${col.javaColumnName};
 </#if>
</#list>
}
