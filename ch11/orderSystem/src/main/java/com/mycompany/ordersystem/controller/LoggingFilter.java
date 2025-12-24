package com.mycompany.ordersystem.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter(
        filterName = "LoggingFilter",
        servletNames = {"customerServlet"}
)
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String remoteAddr = req.getRemoteAddr();
        String httpMethod = req.getMethod();
        String uri = req.getRequestURI();
        String protocol = req.getProtocol();
        int statusCode = resp.getStatus();
        System.out.println("[" + LocalDateTime.now() + "] " + remoteAddr + " : " +
                httpMethod + " " + uri + " " + protocol + " " + statusCode );
        // 이거 안하면 안넘어간다..이게 제일 중요...
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
