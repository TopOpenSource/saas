package com.sdstc.system.service.impl;

import com.sdstc.pub.constant.SystemConstant;
import com.sdstc.pub.utils.StringUtils;
import com.sdstc.rest.oauth.Oauth2Service;
import com.sdstc.system.dto.TokenDto;
import com.sdstc.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private Oauth2Service oauth2Service;

    @Override
    public TokenDto getToken(String account,String pwd,String tenantId) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "password");
            params.put("client_id", SystemConstant.CLIENT_ID);
            params.put("client_secret",SystemConstant.CLIENT_SECRET);
            params.put("username", account);
            params.put("password", pwd);
            if(!StringUtils.isEmpty(tenantId)){
                params.put("tenantId", tenantId);
            }
            return oauth2Service.getToken(params);
        } catch (HttpClientErrorException e) {
            return new TokenDto();
        }
    }
}
