package com.sdstc.oauth2.service;

import java.util.List;

import com.sdstc.dynamicds.model.Tenant;
import com.sdstc.oauth2.model.UserInfo;
import com.sdstc.oauth2.model.UserSecurity;

public interface UserService {
    
	/**
	 * 获取所属客户
	 * @param account
	 * @return
	 */
	List<Tenant> getTenantsByUserAccount(String account);
	
	/**
	   * 获取账户信息
	 * @param account
	 * @return
	 */
	UserInfo getUser(String account);
	
	/**
	 *  拼装 UserSecurity 用于OAuth2验证
	 * @param account
	 * @param tenantId
	 * @param authType  验证类型
	 * @return
	 */
	UserSecurity getUserSecurity(String account,Long tenantId,String authType);
}
