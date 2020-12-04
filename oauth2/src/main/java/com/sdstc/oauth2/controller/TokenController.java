package com.sdstc.oauth2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdstc.oauth2.model.UserInfo;
import com.sdstc.oauth2.model.UserSecurity;
import com.sdstc.pub.dto.LoginUserInfo;
import com.sdstc.pub.common.ResultDto;


@RestController
@RequestMapping("/api/oauth2")
public class TokenController {
	@Autowired
	private ConsumerTokenServices consumerTokenServices;

	@Value("${oauth2.signingKey}")
	private  String signingKey;
	
	@RequestMapping("admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}

	@RequestMapping("user")
	public Principal user(Principal principal,UserInfo dto) {
		//System.out.println(dto);
		return principal;
	}
	
	/**
	   * 获取登录用户信息
	 * @param principal
	 * @return
	 */
	@RequestMapping("userInfo")
	public LoginUserInfo userInfo(Principal principal) {
		OAuth2Authentication oauth2=(OAuth2Authentication) principal;
		UserSecurity user=(UserSecurity) oauth2.getUserAuthentication().getPrincipal();
		return  user.parse2LoginUserInfo();
	}
    /**
	 * 清理token
     * @param authentication
     * @return
     */
	@GetMapping("/removeToken")
	public Boolean removeToken(OAuth2Authentication authentication) {
		//删除redis current
		UsernamePasswordAuthenticationToken userAuth=(UsernamePasswordAuthenticationToken) authentication.getUserAuthentication();
		User user=(User) userAuth.getPrincipal();
		//System.out.println(user.getUsername());
		
		//删除token
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
		consumerTokenServices.revokeToken(details.getTokenValue());
		return true;
	}
	
	/**
	   *   切换 所属客户
	 * @param authentication
	 * @param customerId
	 * @return
	 */
	@RequestMapping("switchCustomer")
	public ResultDto switchCustomer(OAuth2Authentication authentication,Long customerId) {
		/**
		UserSecurity user=(UserSecurity) authentication.getPrincipal();
		user=userService.getUserSecurity(user.getUsername(), customerId);
		
		SecurityContext securityContext =SecurityContextHolder.getContext();
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		securityContext.setAuthentication(token);
		return new ResultDto(1, null);
		**/
		return null;
	}




}
