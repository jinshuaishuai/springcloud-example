package com.jin.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

/**
 * IP过滤器
 * @author shuai.jin
 * @since	2019.10.11 11:55
 *
 */
@Slf4j
public class IPFilter extends ZuulFilter{
	
	@Autowired
	private PropertyConfig propertyConfig;
	
	@Override
	public boolean shouldFilter() {
		return true;		//当此处返回true时，下面的run方法才会执行
	}

	@Override
	public Object run() throws ZuulException {
		String ips = propertyConfig.getIps();
		log.info("IP白名单为：--->{}", ips);
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		
		//获取客户端ip
		String remoteAddr = request.getRemoteAddr();
		log.info("客户端ip为：--->{}", remoteAddr);
		/*
		String ipList = ips.substring(1, ips.length() - 1);
		String[] split = ipList.split(",");
		
		List<String> ipsList = Arrays.asList(split);
		boolean flag = false;
		for(String ip : ipsList) {
			if(remoteAddr.equals(ip)) {
				flag = true;
				break;
			}
		}
		if(flag) {
			context.setResponseStatusCode(200);
			context.setSendZuulResponse(true);
			context.set("isSuccess", true);
			return null;
		} else {
			context.setResponseStatusCode(400);
			context.setSendZuulResponse(false);
			context.set("isSuccess", false);
			return null;
		}

		 */
		//去掉ip白名单拦截
		context.setResponseStatusCode(200);
		context.setSendZuulResponse(true);
		context.set("isSuccess", true);
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {		//过滤器的优先级，0最高，依次递减
		return 0;
	}
	
}
