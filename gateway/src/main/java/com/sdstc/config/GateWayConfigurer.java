package com.sdstc.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfigurer{

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/oauth/**").uri("lb://oauth2"))
				.route(p -> p.path("/api/oauth2/**").uri("lb://oauth2"))
				.route(p -> p.path("/api/baseservice/**").uri("lb://system"))
				.route(p -> p.path("/api/sysservice/**").uri("lb://system"))
				.route(p -> p.path("/api/system/**").uri("lb://system"))
				.route(p -> p.path("/api/order/**").uri("lb://product"))
				.route(p -> p.path("/api/lre_rest/**").uri("lb://project"))
				.route(p -> p.path("/api/lre_project/**").uri("lb://project"))
				.route(p -> p.path("/api/project/**").uri("lb://project"))
				.route(p -> p.path("/api/product/**").uri("lb://product"))
				.route(p -> p.path("/api/forify_project/**").uri("lb://project"))
				.route(p -> p.path("/api/ypjh/**").uri("lb://ypjh"))
				//本地测试使用
//				.route(p -> p.path("/api/system/**").uri("http://localhost:8101"))
//				.route(p -> p.path("/api/order/**").uri("http://localhost:8108"))
//				.route(p -> p.path("/api/sysservice/**").uri("http://localhost:8101"))
//				.route(p -> p.path("/api/baseservice/**").uri("http://localhost:8102"))
//				.route(p -> p.path("/api/project/**").uri("http://localhost:8103"))
//				.route(p -> p.path("/api/lre_rest/**").uri("http://localhost:8103"))
//				.route(p -> p.path("/api/lre_project/**").uri("http://localhost:8104"))
//				.route(p -> p.path("/api/product/**").uri("http://localhost:8108"))
//				.route(p -> p.path("/api/ypjh/**").uri("http://localhost:8109"))
//				.route(p -> p.path("/api/oauth2/**").uri("http://localhost:8002"))
				.build();
	}
}