package com.mycompany.ordersystem.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@WebListener
public class OrderListener implements ServletContextListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // 왜 이게 없으면 한글 출력이 깨지는지 알 수가 없다...
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("서블릿 컨텍스트가 생성되었습니다.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("서블릿 컨텍스트가 소멸되었습니다.");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.printf("%s 어트리뷰트가 추가되었습니다: %s\n", event.getName(), event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.printf("%s 어트리뷰트가 삭제되었습니다: %s\n", event.getName(), event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.printf("%s 어트리뷰트가 변경되었습니다: %s\n", event.getName(), event.getValue());
    }
}
