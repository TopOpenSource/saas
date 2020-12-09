package com.sdstc.sysservice.model;

import com.sdstc.pub.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author system
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysDict extends BaseModel {
    /**
	 * 
	 */
    private Long id;
    /**
	 * 租户ID
	 */
    private Long tenantId;
    /**
	 * 上级ID
	 */
    private Long pId;
    /**
	 * 上级code
	 */
    private String pCode;
    /**
	 * 序号
	 */
    private String index;
    /**
	 * path  上级path/index
	 */
    private String path;
    /**
	 * 字典名称
	 */
    private String name;
    /**
	 * 字典code
	 */
    private String code;
    /**
	 * 是否系统内置 0否 1是
	 */
    private String isSystem;
    /**
	 * 字典说明
	 */
    private String remark;
    /**
	 * 字典类型 0 字典类型 1 字典
	 */
    private String type;
}
