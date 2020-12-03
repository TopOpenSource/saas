package com.sdstc.config.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;

/**
 * cheng
 */

public interface AdviceService {
    /**
     * 是否匹配
     * @return
     */
    public Boolean isMate(MethodParameter parameter);

    /**
     * 请求前处理
     * @return
     */
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter);

    /**
     * 请求后处理
     * @return
     */
    public Object beforeBodyWrite(Object body,MethodParameter parameter);
}
