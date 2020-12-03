package com.sdstc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由设置
 */
@Configuration
public class GateWayConfigurer {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/oauth/**").uri("lb://oauth2"))
				.route(p -> p.path("/api/oauth2/**").uri("lb://oauth2"))
				.route(p -> p.path("/api/system/**").uri("lb://system"))
				//本地测试使用
//				.route(p -> p.path("/api/system/**").uri("http://localhost:8101"))
//				.route(p -> p.path("/api/oauth2/**").uri("http://localhost:8002"))
				.build();
	}
}