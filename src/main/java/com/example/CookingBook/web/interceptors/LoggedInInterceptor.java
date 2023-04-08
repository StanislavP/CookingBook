package com.example.CookingBook.web.interceptors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Component
public class LoggedInInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Cookie myCookie = new Cookie( "job_offer", URLEncoder.encode( "If you are looking here you may want to start working for us.", StandardCharsets.UTF_8) );
        myCookie.setMaxAge(0);
        response.addCookie(myCookie);
    }

}