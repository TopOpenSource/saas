package com.sdstc.gateway.rest.oauth.service.impl;

import com.sdstc.gateway.rest.oauth.service.TokenService;
import com.sdstc.pub.dto.LoginUserInfo;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl implements TokenService {
    @Override
    public LoginUserInfo userInfo() {
        return null;
    }

    @Override
    public LoginUserInfo hasPerm(String url) {
        return null;
    }
}
