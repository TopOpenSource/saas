package com.sdstc.system.model;

import com.sdstc.pub.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author system
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysRole extends BaseModel {
    /**
	 * 
	 */
    private Long id;
    /**
	 * 租户ID
	 */
    private Long tenantId;
    /**
	 * 名称
	 */
    private String name;
    /**
	 * code
	 */
    private String code;
    /**
	 * 序号
	 */
    private String index;
    /**
	 * path  上级path/index
	 */
    private String path;
    /**
	 * 上级ID
	 */
    private Long pId;
}
