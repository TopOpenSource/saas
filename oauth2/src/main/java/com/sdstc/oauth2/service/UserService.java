package com.sdstc.oauth2.service;

import java.util.List;

import com.sdstc.oauth2.model.Tenant;
import com.sdstc.oauth2.model.UserInfo;
import com.sdstc.oauth2.model.UserSecurity;

public interface UserService {


	/**
	 *  拼装 UserSecurity 用于OAuth2验证
	 * @param account
	 * @param tenantId
	 * @param authType  验证类型
	 * @return
	 */
	UserSecurity getUserSecurity(String account,Long tenantId,String authType);
}
