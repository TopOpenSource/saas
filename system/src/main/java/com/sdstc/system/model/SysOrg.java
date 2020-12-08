package com.sdstc.system.model;

import com.sdstc.pub.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author system
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysOrg extends BaseModel {
    /**
	 * 
	 */
    private Long id;
    /**
	 * 组织名称
	 */
    private String name;
    /**
	 * 简写
	 */
    private String code;
    /**
	 * 上级组织ID
	 */
    private Long pId;
    /**
	 * 序号
	 */
    private String index;
    /**
	 * path  上级path/index
	 */
    private String path;
    /**
	 * 机构类型： 单位1、部门2、岗位3
	 */
    private String type;
}
