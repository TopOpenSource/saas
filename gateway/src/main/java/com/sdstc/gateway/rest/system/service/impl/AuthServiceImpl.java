package com.sdstc.gateway.rest.system.service.impl;

import com.sdstc.gateway.rest.system.service.AuthService;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService {
    @Override
    public Boolean hasPerm() {
        return false;
    }
}
