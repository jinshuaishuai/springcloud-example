package com.jin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	/**
	 * token过滤器
	 * @return
	 */
//	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}
	
	/**
	 * IP白名单过滤器
	 * @return
	 */
	@Bean
	public IPFilter ipFilter() {
		return new IPFilter();
	}
}
