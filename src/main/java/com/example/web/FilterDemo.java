package com.example.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		String initParam = filterConfig.getInitParameter("ref") ;	
		System.out.println("** 过滤器初始化，初始化参数=" + initParam) ;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("** 执行doFilter()方法之前") ;
		chain.doFilter(request,response) ;
		System.out.println("** 执行doFilter()方法之后") ;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
