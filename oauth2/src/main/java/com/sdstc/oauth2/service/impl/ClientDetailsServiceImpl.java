package com.sdstc.oauth2.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sdstc.dynamicds.config.DBContextHolder;
import com.sdstc.dynamicds.constant.TenantConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.sdstc.oauth2.dao.ClientDao;
import com.sdstc.oauth2.model.Client;

import lombok.extern.slf4j.Slf4j;

/**
 * 客户端详情
 * 
 * @author cheng
 *
 */
@Service("clientDetailsServiceJdbc")
@Slf4j
public class ClientDetailsServiceImpl implements ClientDetailsService {
	@Autowired
    private ClientDao  clientDao;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		//设置默认数据源
		DBContextHolder.setDbKey(TenantConstant.defaultDBKey);
		log.debug("clientDetailsServiceJdbc"+clientId);

		Client client=clientDao.getClientById(clientId);
		BaseClientDetails baseClientDetails = new BaseClientDetails();
		// 用来标识客户的Id
		baseClientDetails.setClientId(client.getClientId());
		// 用来标识客户端的安全码
		baseClientDetails.setClientSecret(client.getClientPwd());

		// 用来限制客户端的访问范围
		baseClientDetails.setScope(client.getScopes());

		// 用来标识客户端使用的授权类型
		baseClientDetails.setAuthorizedGrantTypes(client.getAuthorizedGrantTypes());

		// 此客户端可以使用的权限
		List<String> authoritiesStrs=client.getAuthorities();
		if(authoritiesStrs!=null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(String authoritiesStr:authoritiesStrs) {
				authorities.add(new SimpleGrantedAuthority(authoritiesStr));
			}
			baseClientDetails.setAuthorities(authorities);
		}else {
			baseClientDetails.setAuthorities(null);
		}
		// 设置返回地址
		baseClientDetails.setRegisteredRedirectUri(client.getRedirectUris());
		//baseClientDetails.isAutoApprove(client.getIsAutoApprove());
		List<String> autoApproves=new ArrayList<>();
		autoApproves.add(client.getIsAutoApprove());
		baseClientDetails.setAutoApproveScopes(autoApproves);
		return baseClientDetails;
	}

}
