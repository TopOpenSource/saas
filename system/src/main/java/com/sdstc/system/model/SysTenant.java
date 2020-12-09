package com.sdstc.system.model;

import com.sdstc.pub.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author system
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysTenant extends BaseModel {
    /**
	 * 
	 */
    private Long id;
    /**
	 * 租户名称
	 */
    private String name;
    /**
	 * 0 注销 1正常
	 */
    private String state;
}
