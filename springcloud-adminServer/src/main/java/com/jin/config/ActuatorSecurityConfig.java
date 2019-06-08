package com.jin.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
		如果你的项目启用了Spring Security，此时，如果你想访问监控服务的主页面，那么，你就要加下面这个配置了
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests().anyRequest().permitAll();
	}
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.requestMatcher(EndpointRequest.toAnyEndpoint()
//				).authorizeRequests().anyRequest().hasRole("ENDPOINT_ADMIN").and().httpBasic();
//	}
}
