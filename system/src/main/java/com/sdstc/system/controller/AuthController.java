package com.sdstc.system.controller;

import com.sdstc.gateway.rest.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限认证
 * @author cheng
 */
@RestController
@RequestMapping("/api/system/auth")
public class AuthController {
  @Autowired
    private TestService testService;
    /**
     * 判断url是否有权限
     * @param url
     * @return
     */
    @GetMapping("hasPerm")
    public boolean hasPerm(String url) {
        return false;
    }

}
