package com.sdstc.config.advice;

import com.sdstc.dynamicds.constant.DataSourceConstant;
import com.sdstc.dynamicds.start.DBContextHolder;
import com.sdstc.pub.constant.SystemConstant;
import com.sdstc.pub.dto.LoginUserInfo;
import com.sdstc.pub.utils.StringUtils;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author cheng
 * 请求拦截器，将请求中header加入常量
 */
@RestControllerAdvice
public class DynamicDataSourceRestControllerAdvice {

    @ModelAttribute
    public void setTenantId(HttpServletRequest request) {
        String tenantId = request.getHeader(SystemConstant.TENANTID_HEADER);
        String userName =request.getHeader(SystemConstant.LOGIN_USER_NAME);
        String userAccount =request.getHeader(SystemConstant.LOGIN_USER_ACCOUNT);
        String userId =request.getHeader(SystemConstant.LOGIN_USER_ID);

        if(!StringUtils.isEmpty(tenantId)){
            DBContextHolder.setTenantId(tenantId);
            DBContextHolder.setDbKey(tenantId);
            LoginUserInfo.setLoginUserInfo(userId,userAccount,userName,tenantId);
        }
    }
}
