package com.sdstc.gateway.config;

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
    private final String AUTHORIZATION_HEADER = "Authorization";

    private static final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    public static void setToken(String token) {
        tokenThreadLocal.set(token);
    }

    /**
     * 拦截器增加token
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION_HEADER,tokenThreadLocal.get());
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
