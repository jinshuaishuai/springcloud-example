package com.jin.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class KeyResolverConfig {

	/*
	 * 通过IP进行限流
	 */
	@Bean
	public KeyResolver hostKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
	
	/*
	 * 通过URI进行限流
	 */
//	@Bean
//	public KeyResolver uriKeyResolver() {
//		return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
//	}

	/*
	 * 根据用户的维度进行限流
	 */
//	@Bean
//	public KeyResolver userKeyResolver() {
//		return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//	}
}
