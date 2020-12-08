package com.sdstc.oauth2.controller;

import com.sdstc.oauth2.model.UserSecurity;
import com.sdstc.oauth2.service.RoleService;
import com.sdstc.pub.dto.LoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/oauth2/auth")
public class AuthController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("hasPerm")
    public LoginUserInfo userInfo(Principal principal,String url) {
        OAuth2Authentication oauth2=(OAuth2Authentication) principal;
        UserSecurity user=(UserSecurity) oauth2.getUserAuthentication().getPrincipal();
        LoginUserInfo loginUserInfo=user.parse2LoginUserInfo();
        loginUserInfo.setHasPerm(roleService.hasPerm(loginUserInfo.getId(),url,String.valueOf(loginUserInfo.getTenantId())));
        return loginUserInfo;
    }

}
