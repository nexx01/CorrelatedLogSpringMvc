package com.example.correlatedlogspringmvc.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MdcLogEnhancerFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--------------------------------");
        System.out.println("Filter configuration end");
        System.out.println("--------------------------------");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        var requestId = httpRequest.getHeader("requestId");



        System.out.println("-----------doOnFilter-----------");
        MDC.put("requestId", requestId);
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.remove("requestId");//Otherwise memory overflow
        //Console.log("======{}",MDC.get("requestId"));//Output null
    }
}

