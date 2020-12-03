package com.sdstc.gateway.rest.oauth.service;

import com.sdstc.gateway.rest.oauth.dto.LoginUserInfo;
import com.sdstc.gateway.rest.oauth.service.impl.TokenServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "oauth2")
public interface TokenService {
    @GetMapping("/api/oauth2/userInfo")
    LoginUserInfo userInfo();
}
