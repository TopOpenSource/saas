package com.sdstc.oauth2.integration.authenticator.impl;

import com.sdstc.oauth2.integration.IntegrationAuthentication;
import com.sdstc.oauth2.integration.authenticator.IntegrationAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdstc.oauth2.model.UserSecurity;
import com.sdstc.oauth2.service.UserService;

/**
 * 短信验证码登录
 * @author cheng
 *
 */
@Component
public class SMSAuthenticator implements IntegrationAuthenticator {
	@Autowired
	private UserService userService;
	
	@Override
	public UserSecurity authenticate(IntegrationAuthentication integrationAuthentication) {
		return userService.getUserSecurity(integrationAuthentication.getUsername(), null,integrationAuthentication.getAuthType());
	}

	@Override
	public void prepare(IntegrationAuthentication integrationAuthentication) {
		//此处可以抛出异常信息
	}

	@Override
	public boolean support(IntegrationAuthentication integrationAuthentication) {
		 return IntegrationAuthentication.SMS.equals(integrationAuthentication.getAuthType());
	}

	@Override
	public void complete(IntegrationAuthentication integrationAuthentication) {
			
	}

}
