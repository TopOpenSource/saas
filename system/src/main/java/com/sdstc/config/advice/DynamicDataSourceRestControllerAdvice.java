package com.sdstc.config.advice;

import com.sdstc.dynamicds.constant.DataSourceConstant;
import com.sdstc.dynamicds.start.DBContextHolder;
import com.sdstc.pub.constant.SystemConstant;
import com.sdstc.pub.dto.LoginUserInfo;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestControllerAdvice
public class DynamicDataSourceRestControllerAdvice {

    @ModelAttribute
    public void setTenantId(HttpServletRequest request) {
        String tenantId = request.getHeader(SystemConstant.TENANTID_HEADER);
        String userName =request.getHeader(SystemConstant.LOGIN_USER_NAME);
        String userAccount =request.getHeader(SystemConstant.LOGIN_USER_ACCOUNT);
        String userId =request.getHeader(SystemConstant.LOGIN_USER_ID);

        DBContextHolder.setTenantId(tenantId);
        DBContextHolder.setDbKey(tenantId);
        LoginUserInfo.setLoginUserInfo(userId,userAccount,userName,tenantId);
    }
}
