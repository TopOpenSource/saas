package com.sdstc.gateway.config;

import com.sdstc.pub.constant.SystemConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.stream.Collectors;

@Configuration
public class FeignOauth2RequestInterceptor implements RequestInterceptor {
    /**
     * token 线程安全
     */
    private static final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();
    public static void setToken(String token) {
        tokenThreadLocal.set(token);
    }
  /*  *//**
     * tenantId 线程安全
     *//*
    private static final ThreadLocal<String> tenantIdThreadLocal = new ThreadLocal<>();
    public static void setTenantId(String tenantId) {
        tenantIdThreadLocal.set(tenantId);
    }*/
    /**
     * 拦截器增加token tenantId
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(SystemConstant.AUTHORIZATION_HEADER,tokenThreadLocal.get());
        //requestTemplate.header(SystemConstant.TENANTID_HEADER,tenantIdThreadLocal.get());
    }

    /**
     * 设置数据转换
     * @param converters
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
