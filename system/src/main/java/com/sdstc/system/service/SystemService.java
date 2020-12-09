package com.sdstc.system.service;

import com.sdstc.system.dto.TokenDto;

public interface SystemService {

    TokenDto getToken(String account,String pwd,String tenantId);
}
