package com.jin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {
	//将该过滤器注入到应用程序上下文中
	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}
}
