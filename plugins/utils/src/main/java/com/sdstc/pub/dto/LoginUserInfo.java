package com.sdstc.pub.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 登陆者信息
 * @author cheng
 *
 */
@Data
public class LoginUserInfo {
    private Long id;
    private String userAccount;
    private String userName;
    private Long tenantId;
    private String tenantName;
    private String tenantState;
    private List<String> userAuths;
    private String phone;
    private String email;
    private Boolean hasPerm;

    public void addAuth(String auth) {
        if(this.userAuths==null) {
            this.userAuths=new ArrayList<String>();
        }
        this.userAuths.add(auth);
    }

    private static final ThreadLocal<LoginUserInfo> LoginUser = new ThreadLocal<>();

    public static void setLoginUserInfo(String id,String userAccount,String userName,String tenantId){
        LoginUserInfo loginUserInfo=new LoginUserInfo();
        loginUserInfo.setId(Long.valueOf(id));
        loginUserInfo.setUserAccount(userAccount);
        loginUserInfo.setUserName(userName);
        loginUserInfo.setTenantId(Long.valueOf(tenantId));

        LoginUser.set(loginUserInfo);
    }

    public static LoginUserInfo getLoginUserInfo() {
        return LoginUser.get();
    }


}
