package com.sdstc.gateway.rest.system.service;

import com.sdstc.gateway.rest.system.service.impl.AuthServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "system",fallback = AuthServiceImpl.class)
public interface AuthService {
    @GetMapping("/api/system/auth/hasPerm")
    Boolean  hasPerm();
}
