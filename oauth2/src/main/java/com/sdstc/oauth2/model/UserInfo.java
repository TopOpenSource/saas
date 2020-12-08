package com.sdstc.oauth2.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
	private Long id;
	private String account;
	private String name;
	//密码
	private String pwd;
	//短信验证码
	private String smsCode;
	//客户ID
	private Long customerId;
	
	private String state;
	private String isDelete;
	
	private String phone;
	private String email;
	
	public UserInfo() {
		
	}
	public UserInfo(String account,Long customerId) {
		this.account=account;
		this.customerId=customerId;
	}
}
