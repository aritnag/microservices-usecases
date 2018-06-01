package com.restful.microservices.example.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulFilterComponent extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
		logger.info("request --> {} request uri -->{}",httpServletRequest,httpServletRequest.getRequestURI());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
