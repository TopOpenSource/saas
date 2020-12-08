package com.sdstc.system.controller;

import com.sdstc.pub.dto.LoginUserInfo;
import com.sdstc.system.dao.UserDao;
import com.sdstc.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/system/test")
public class TestController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("insert")
    public String insert() {

        List<User> users = userDao.selUsers();
        return "a";
    }
}
