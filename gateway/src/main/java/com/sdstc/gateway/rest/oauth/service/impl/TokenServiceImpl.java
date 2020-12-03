package com.sdstc.gateway.rest.oauth.service.impl;

import com.sdstc.gateway.rest.oauth.dto.LoginUserInfo;
import com.sdstc.gateway.rest.oauth.service.TokenService;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl implements TokenService {
    @Override
    public LoginUserInfo userInfo() {
        return null;
    }
}
