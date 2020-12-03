package com.sdstc.oauth2.integration.authenticator.impl;

import com.sdstc.oauth2.integration.IntegrationAuthentication;
import com.sdstc.oauth2.integration.authenticator.IntegrationAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdstc.oauth2.model.UserSecurity;
import com.sdstc.pub.utils.StringUtils;
import com.sdstc.oauth2.service.UserService;

/**
 * 用户名密码登录
 * @author cheng
 *
 */
@Component
public class PWDAuthenticator implements IntegrationAuthenticator {
	@Autowired
	private UserService userService;
	
	@Override
	public UserSecurity authenticate(IntegrationAuthentication integrationAuthentication) {
		String tenantIdStr=integrationAuthentication.getAuthParameter("tenantId");
		//如果指定了租户则设置租户ID
		Long tenantId=null;
		if(tenantIdStr!=null){
			tenantId=Long.parseLong(tenantIdStr);
		}
		return userService.getUserSecurity(integrationAuthentication.getUsername(), tenantId,integrationAuthentication.getAuthType());
	}

	@Override
	public void prepare(IntegrationAuthentication integrationAuthentication) {
		
	}

	@Override
	public boolean support(IntegrationAuthentication integrationAuthentication) {
		 return IntegrationAuthentication.PWD.equals(integrationAuthentication.getAuthType())||StringUtils.isEmpty(integrationAuthentication.getAuthType());
	}

	@Override
	public void complete(IntegrationAuthentication integrationAuthentication) {
			
	}

}
