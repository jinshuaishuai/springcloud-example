package com.jin.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TokenFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		
		log.info(request.getMethod() + "----->" + request.getRequestURI());
		String token = request.getParameter("token");
		if(StringUtils.isNotBlank(token)) {
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
		
		
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
