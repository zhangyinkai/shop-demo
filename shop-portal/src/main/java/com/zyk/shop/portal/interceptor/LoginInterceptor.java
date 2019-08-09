package com.zyk.shop.portal.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("login interceptor check ...");
        Object user =  request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect("/login.html");
            return false;
        }
        log.info("login success...");
        return true;
    }

}
