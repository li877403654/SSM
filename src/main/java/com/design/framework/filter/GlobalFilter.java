package com.design.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 全局过滤器
 * @author JohnDeng
 * @date 2018年8月8日下午3:35:54
 */
@WebFilter(urlPatterns = "/*")
public class GlobalFilter implements Filter {

	protected Logger logger = Logger.getLogger(getClass());

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		logger.debug("\n===================globalFilter begin===================");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("content-type", "application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response.setHeader("Access-Control-Allow-Headers","x-requested-with, Content-Type, Token, MUserAgent");
		
		logger.debug(request.getRequestURI() + "\t" + request.getContextPath());
		logger.debug("前一个链接是：" + request.getHeader("referer"));
		logger.debug("访问的接口是：" + request.getRequestURI());
		logger.debug("请求方法是：" + request.getMethod());
		logger.debug("获取到的Token是：" + request.getHeader("Token"));
		
		logger.debug("\n===================globalFilter end===================");
		
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
		
	}
	


}
