package com.sdstc.config.advice.dynamicds;

import com.sdstc.config.advice.AdviceService;
import com.sdstc.dynamicds.config.DBContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设置租户数据源
 */
@Service
public class DynamicdsAdvice implements AdviceService {

    @Override
    public Boolean isMate(MethodParameter parameter) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter) {
        /**
         * 根据header中的租户ID设置数据源
         */
        List<String> tenantIds = inputMessage.getHeaders().get("tenantId");
        DBContextHolder.setDbKey(tenantIds.get(0));
        return inputMessage;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter parameter) {
        return body;
    }
}
