package com.sdstc.system.model;

import com.sdstc.pub.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author system
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysUrl extends BaseModel {
    /**
	 * 
	 */
    private Long id;
    /**
	 * url名称
	 */
    private String name;
    /**
	 * url
	 */
    private String url;
    /**
	 * 上级urlID
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
}
