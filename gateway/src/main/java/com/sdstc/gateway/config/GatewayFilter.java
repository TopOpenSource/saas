package com.sdstc.gateway.config;

import com.sdstc.gateway.rest.oauth.dto.LoginUserInfo;
import com.sdstc.gateway.rest.oauth.service.TokenService;
import com.sdstc.gateway.rest.system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author cheng
 * gateway全局拦截
 */
@Component
public class GatewayFilter implements GlobalFilter, Ordered {
    private final String AUTHORIZATION_HEADER = "Authorization";
    @Autowired
    private AuthService authService;
    @Autowired
    private TokenService tokenService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request=exchange.getRequest();

        String method = request.getMethodValue();
        String url = request.getPath().value();

        List<String> tokens = request.getHeaders().get(AUTHORIZATION_HEADER);
        FeignOauth2RequestInterceptor.setToken(tokens.get(0));
        LoginUserInfo userInfo= tokenService.userInfo();
        /*//判断url是否需要权限
        boolean isAuth = permissionService.ignoreAuthentication(url);
        if(isAuth){
            //验证token
            String token=this.getToken(request);

            String authentication = permissionService.invalidAccessToken(token,url,request.getCookies());
            boolean permission = permissionService.hasPermission(authentication, url ,method);

            return chain.filter(exchange);
        }else{
            return chain.filter(exchange);
        }*/

        return chain.filter(exchange);
    }

    /**
     * 从请求中获取token
     * @param request
     * @return
     */
    private String getToken(ServerHttpRequest request){
        return null;
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
