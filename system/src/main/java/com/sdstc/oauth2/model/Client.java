package com.sdstc.oauth2.model;

import com.sdstc.pub.utils.StringUtils;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Client {
	private String id;
	private String clientId;
	private String clientPwd;
	private String scopes;
	private String authorizedGrantTypes;
	private String authorities;
	private String redirectUris;
	private String isAutoApprove;

	public List<String> getScopes() {
		return StringUtils.parseString2ArrayList(this.scopes, ",");
	}
	
	public List<String> getAuthorizedGrantTypes() {
		return StringUtils.parseString2ArrayList(this.authorizedGrantTypes, ",");
	}
	
	public List<String> getAuthorities() {
		return StringUtils.parseString2ArrayList(this.authorities, ",");
	}
	public Set<String> getRedirectUris(){
		return StringUtils.parseString2HashSet(this.redirectUris, ",");
	}
	
	public static void main(String[] args) {
		Client client1=new Client();
		client1.setClientId("11");
		System.out.println(client1.getClientId());
	}
}
