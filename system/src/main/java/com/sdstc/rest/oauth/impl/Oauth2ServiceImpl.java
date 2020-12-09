package com.sdstc.rest.oauth.impl;

import com.sdstc.rest.oauth.Oauth2Service;
import com.sdstc.system.dto.TokenDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Oauth2ServiceImpl implements Oauth2Service {
    @Override
    public TokenDto getToken(Map<String, String> parameters) {
        return null;
    }
}
