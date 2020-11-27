package com.sdstc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class CorsConfig {
 
	  @Bean
	    public CorsWebFilter corsFilter() {
	        CorsConfiguration config = new CorsConfiguration();

	        config.setAllowCredentials(true); // 允许cookies跨域

		    config.addAllowedOrigin("https://www.yuntest.top");
		    config.addAllowedOrigin("https://account.yuntest.top");
		    config.addAllowedOrigin("https://youpin.yuntest.top");


		    config.addAllowedOrigin("https://testaccount.yuntest.top");
		    config.addAllowedOrigin("https://testyoupin.yuntest.top");
		    config.addAllowedOrigin("https://testadmin.yuntest.top");


            config.addAllowedOrigin("http://47.99.33.214:30106");
		    config.addAllowedOrigin("http://127.0.0.1:8080");
		    config.addAllowedOrigin("http://localhost:8080");

		    config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
	        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
	        config.addAllowedMethod("OPTIONS");// 允许提交请求的方法类型，*表示全部允许
	        config.addAllowedMethod("HEAD");
	        config.addAllowedMethod("GET");
	        config.addAllowedMethod("PUT");
	        config.addAllowedMethod("POST");
	        config.addAllowedMethod("DELETE");
	        config.addAllowedMethod("PATCH");

	        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source =
	                new org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource(new PathPatternParser());
	        source.registerCorsConfiguration("/**", config);

	        return new CorsWebFilter(source);
	  }
}
