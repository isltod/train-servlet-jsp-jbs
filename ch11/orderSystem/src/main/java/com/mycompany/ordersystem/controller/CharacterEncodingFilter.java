package com.mycompany.ordersystem.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@WebFilter(
        filterName = "CharacterEncodingFilter",
        urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8")}
)
public class CharacterEncodingFilter implements Filter {

    private String encoding = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
        // 왜 이게 없으면 한글 출력이 깨지는지 알 수가 없다...
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(filterConfig.getFilterName() + " 필터가 시작되었습니다.");
        System.out.println("필터 초기 변수는 encoding: " + this.encoding + " 입니다.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            if (encoding != null) {
                request.setCharacterEncoding(encoding);
                response.setCharacterEncoding(encoding);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 자바는 대체적으로 소멸자에서는 할게 없음
    }
}
