package com.jin.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;

import lombok.Data;
import lombok.ToString;

@Component
@Data
@ToString
public class PageConfig {
	
	@Value("${pagehelper.pagehelperDialect}")
	private String pagehelperDialect;
	
	@Value("${pagehelper.reasonable}")
	private String reasonable;
	
	@Value("${pagehelper.supportMethodsArguments}")
	private String supportMethodsArguments;
	
	@Value("${pagehelper.params}")
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
