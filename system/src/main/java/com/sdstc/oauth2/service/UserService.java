package com.sdstc.oauth2.service;

import com.sdstc.oauth2.model.Customer;
import com.sdstc.oauth2.model.UserInfo;
import com.sdstc.oauth2.model.UserSecurity;

import java.util.List;

public interface UserService {
    
	/**
	 * 获取所属客户
	 * @param user
	 * @return
	 */
	List<Customer> getCustomersByUser(UserInfo user);
	
	/**
	   * 获取账户信息
	 * @param account
	 * @return
	 */
	UserInfo getUser(String account);
	
	/**
	 *  拼装 UserSecurity 用于OAuth2验证
	 * @param account
	 * @param customerId
	 * @param authType  验证类型
	 * @return
	 */
	UserSecurity getUserSecurity(String account, Long customerId, String authType);
}
