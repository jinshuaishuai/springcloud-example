package com.jin.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {			//相当于创建一个application-rabbitconfig.xml文件。
	
	/*
	 * <bean class= "Queue" id = "queue">
	 * 	
	 * </bean>
	 */
	
	@Bean
	public Queue queue() {
		return new Queue("Hello");
	}
}
