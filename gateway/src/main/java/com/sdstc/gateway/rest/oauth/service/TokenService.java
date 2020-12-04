package com.sdstc.gateway.rest.oauth.service;

import com.sdstc.gateway.rest.oauth.service.impl.TokenServiceImpl;
import com.sdstc.pub.dto.LoginUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oauth2")
public interface TokenService {
    @GetMapping("/api/oauth2/userInfo")
    LoginUserInfo userInfo();
    @GetMapping("/api/oauth2/auth/hasPerm")
    LoginUserInfo hasPerm(@RequestParam(name = "url", required = true) String url);
}
