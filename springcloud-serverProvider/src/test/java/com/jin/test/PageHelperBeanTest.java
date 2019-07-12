package com.jin.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jin.config.PageConfig;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageHelperBeanTest {
	
	@Autowired
	private PageConfig config;
	
	
	@Test
	public void getMybatisConfigBean() {
		System.out.println(config.toString());
	}
}
