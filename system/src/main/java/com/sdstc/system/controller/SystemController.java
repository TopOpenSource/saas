package com.sdstc.system.controller;


import com.sdstc.pub.common.CommonDto;
import com.sdstc.pub.common.ResultDto;
import com.sdstc.pub.utils.StringUtils;
import com.sdstc.system.dto.TokenDto;
import com.sdstc.system.service.SystemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用
 */
@RestController
@RequestMapping("/api/system")
@Log4j2
public class SystemController {
    @Autowired
    private SystemService systemService;
    /**
     * 登录
     * @param dto
     * @return
     */
    @RequestMapping("login")
    public TokenDto login(@RequestBody CommonDto dto) {
        String account=dto.getAsString("account");
        String pwd=dto.getAsString("pwd");
        String tenantId=dto.getAsString("tenantId");

        if(StringUtils.isEmpty(account)||StringUtils.isEmpty(pwd)) {
            return new TokenDto();
        }else{
            return systemService.getToken(account,pwd,tenantId);
        }
    }

    /**
     * 登出
     * @param token
     * @return
     */
    @RequestMapping("logout")
    public ResultDto logout(String token) {

        return null;
    }
}
