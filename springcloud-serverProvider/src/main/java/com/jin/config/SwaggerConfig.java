package com.jin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//开启Swagger配置
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket document() {
		return new Docket(DocumentationType.SWAGGER_2)
				//文档的基础信息
				.apiInfo(apiInfo())
				
				.select()
				//扫描的包
				.apis(RequestHandlerSelectors.basePackage("com.jin.controller"))
				//该包下的所有方法
				.paths(PathSelectors.any())
				.build();
	}

	public ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				//文章标题
				.title("需求文档，有问题及时和作者联系")
				//创建人信息.
				.contact(new Contact("shuai.jin", "http://www.baidu.com", "jin2111843364@163.com"))
				//版本号
				.version("1.0")
				//描述
				.description("微服务练习系统")
				.build();
	}
}
