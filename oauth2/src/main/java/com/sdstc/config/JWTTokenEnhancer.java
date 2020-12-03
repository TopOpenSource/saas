package com.sdstc.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.sdstc.oauth2.model.UserSecurity;

/**
 * token 扩展
 * @author cheng
 *
 */
@Component("jwtTokenEnhancer")
public class JWTTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		List<String> auths =authentication.getAuthorities().stream().map(auth->{
			return auth.getAuthority();
		}).collect(Collectors.toList());
		
		UserSecurity user=(UserSecurity) authentication.getPrincipal();
		Map<String, Object> info = new HashMap<>();
        info.put("tenant",user.getTenant());
        info.put("tenants",user.getTenants());
        info.put("userName",user.getUserName());
        info.put("userAccount", user.getUsername());
        info.put("phone", user.getPhone());
        info.put("email", user.getEmail());
        info.put("auths", auths);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
	}

}
