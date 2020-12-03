package com.sdstc.gateway.config;

import com.sdstc.gateway.service.PermissionService;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhangzhiwei
 * gateway全局拦截
 */
@Component
public class GatewayFilter implements GlobalFilter, Ordered {

    protected static ThreadLocal<String> ip = new ThreadLocal<>();

    private PermissionService permissionService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request=exchange.getRequest();

        String method = request.getMethodValue();
        String url = request.getPath().value();

        //判断url是否需要权限
        boolean isAuth = permissionService.ignoreAuthentication(url);
        if(isAuth){
            //验证token
            String token=this.getToken(request);

            String authentication = permissionService.invalidAccessToken(token,url,request.getCookies());
            boolean permission = permissionService.hasPermission(authentication, url ,method);

            return chain.filter(exchange);
        }else{
            return chain.filter(exchange);
        }

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
