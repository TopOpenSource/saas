package com.sdstc.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源配置类
 * @author cheng
 *
 */
@Configuration
@EnableResourceServer
@Order(Integer.MIN_VALUE)
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		super.configure(resources);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http
         .requestMatchers().antMatchers("/api/**")
         .and()
         .authorizeRequests()
         .antMatchers("/api/**").authenticated();
	}

}
