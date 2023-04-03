package com.example.CookingBook.web.interceptors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URLEncoder;


@Component
public class LoggedInInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Cookie myCookie = new Cookie( "job_offer", URLEncoder.encode( "If you are looking here you may want to start working for us.", "UTF-8" ) );
//        Cookie myCookie = new Cookie("job_offer", "If you are looking here, you may want to start working for us.");
        myCookie.setMaxAge(0);
        response.addCookie(myCookie);
    }
}