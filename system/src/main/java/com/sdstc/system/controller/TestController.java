package com.sdstc.system.controller;


import com.sdstc.pub.dto.LoginUserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/system")
public class TestController {
    @RequestMapping("user/add")
    public String test1() {
        System.out.print(LoginUserInfo.getLoginUserInfo().getUserName());
        return "ok";
    }

    @RequestMapping("org/add")
    public String test2() {
        System.out.print(LoginUserInfo.getLoginUserInfo().getUserName());
        return "ok";
    }


    @RequestMapping("role/add")
    public String test3() {
        System.out.print(LoginUserInfo.getLoginUserInfo().getUserName());
        return "ok";
    }

    @RequestMapping("perm/add")
    public String test4() {
        System.out.print(LoginUserInfo.getLoginUserInfo().getUserName());
        return "ok";
    }

    @RequestMapping("url/add")
    public String test5() {
        System.out.print(LoginUserInfo.getLoginUserInfo().getUserName());
        return "ok";
    }
}
