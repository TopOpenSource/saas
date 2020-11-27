package com.sdstc.config;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class RequestRecordFilter implements GlobalFilter, Ordered{

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerWebExchange build;
		
		ServerHttpRequest request=exchange.getRequest();
        
        //记录访问地址和url
        String remoteAddr=request.getRemoteAddress().toString();
        String url=request.getPath().toString();
        log.info(remoteAddr+"-"+url);
        //如果params包含token，则加入header
        MultiValueMap<String, String> params=request.getQueryParams();
        List<String> tokens=params.get("AuthorizationToken");
        if(tokens!=null && tokens.size()==1) {
            ServerHttpRequest host = exchange.getRequest().mutate().header("Authorization", "Bearer  "+tokens.get(0)).build();
            build = exchange.mutate().request(host).build();
        }else {
        	build=exchange.mutate().request(exchange.getRequest()).build();
        }
        return chain.filter(build);
	}

}
