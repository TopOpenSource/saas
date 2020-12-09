package com.sdstc.sysservice.model;

import com.sdstc.pub.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * @author system
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysFile extends BaseModel {
    /**
	 * 
	 */
    private Long id;
    /**
	 * 租户ID
	 */
    private Long tenantId;
    /**
	 * 文件名
	 */
    private String name;
    /**
	 * 文件大小
	 */
    private BigDecimal size;
    /**
	 * 文件所在路径
	 */
    private String path;
    /**
	 * 文件类型
	 */
    private String type;
    /**
	 * 图片高度
	 */
    private BigDecimal imageHigh;
    /**
	 * 图片宽度
	 */
    private BigDecimal imageWidth;
    /**
	 * 存储类型 0 oss 1本地
	 */
    private String storeType;
    /**
	 * OSS专用  
	 */
    private String bucket;
}
