package com.sdstc.gateway.rest.oauth.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 登陆者信息
 * @author cheng
 *
 */
@Data
public class LoginUserInfo {
   private String userAccount;
   private String userName;
   private Long tenantId;
   private String tenantName;
   private String tenantState;
   private List<String> userAuths;
   private String phone;
   private String email;
   
   public void addAuth(String auth) {
	   if(this.userAuths==null) {
		   this.userAuths=new ArrayList<String>();
	   }
       this.userAuths.add(auth);
   }
}
