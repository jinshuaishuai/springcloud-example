package com.jin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@RefreshScope
@Data
public class PropertyConfig {
	
	@Value("${ip}")
	private String ips;
}
