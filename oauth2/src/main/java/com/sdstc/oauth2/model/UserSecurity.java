package com.sdstc.oauth2.model;

import com.sdstc.pub.dto.LoginUserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class UserSecurity extends User {

	public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	private static final long serialVersionUID = 682317142008466189L;
	// 用户名称
	private String userName;
    //所属客户列表
	private List<Tenant> tenants;
	//所选客户
	private  Tenant tenant;

	private Long id;
	private String phone;
	private String email;

	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Tenant> getTenants() {
		return tenants;
	}
	public void setTenants(List<Tenant> customers) {
		this.tenants = customers;
	}
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 转为 LoginUserInfo
	 * @return
	 */
	public LoginUserInfo parse2LoginUserInfo(){
		LoginUserInfo userInfo=new LoginUserInfo();

		if(this.getTenant()!=null && this.getTenant().getId() !=null){
			userInfo.setTenantId(this.getTenant().getId());
			userInfo.setTenantName(this.getTenant().getName());
			userInfo.setTenantState(this.getTenant().getState());
		}
		userInfo.setId(this.getId());
		userInfo.setUserAccount(this.getUsername());
		userInfo.setUserName(this.getUserName());
		userInfo.setPhone(this.getPhone());
		userInfo.setEmail(this.getEmail());
		for(GrantedAuthority auth:this.getAuthorities()) {
			userInfo.addAuth(auth.getAuthority());
		}
		return userInfo;
	}
	
}
