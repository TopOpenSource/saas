package com.sdstc.system.model;

import com.sdstc.pub.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author system
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysUser extends BaseModel {
    /**
	 * 
	 */
    private Long id;
    /**
	 * 租户ID
	 */
    private Long tenantId;
    /**
	 * 姓名
	 */
    private String name;
    /**
	 * 账号
	 */
    private String account;
    /**
	 * 手机号
	 */
    private String phone;
    /**
	 * 邮箱
	 */
    private String email;
    /**
	 * 密码
	 */
    private String pwd;
    /**
	 * 0 注销 1正常
	 */
    private String state;
}
