package com.jin.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "pagehelper")
@ToString
public class PageConfig {
	
	@Value("${pagehelperDialect}")
	private String pagehelperDialect;
	
	@Value("${reasonable}")
	private String reasonable;
	
	@Value("${supportMethodsArguments}")
	private String supportMethodsArguments;
	
	@Value("${params}")
	private String params;
	
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		
		Properties prop = new Properties();
		prop.setProperty("pagehelperDialect", pagehelperDialect);
		prop.setProperty("reasonable", reasonable);
		prop.setProperty("supportMethodsArguments", supportMethodsArguments);
		prop.setProperty("parms", params);
		pageHelper.setProperties(prop);
		
		return pageHelper;
	}
}
