package com.sdstc.system.dto;

import com.sdstc.system.model.SysTenant;
import lombok.Data;

import java.util.List;

@Data
public class TokenDto {
	private String access_token;
	private String refresh_token;
	private Integer expires_in;
	
	private String userAccount;
	private String userName;
	private SysTenant tenant;
	private List<SysTenant> tenants;
	private List<String> auths;

	public TokenDto() {
		
	}
}
