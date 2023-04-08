package com.example.CookingBook.config;

import com.example.CookingBook.web.interceptors.LoggedInInterceptor;
import com.example.CookingBook.web.interceptors.TitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebMVCConfiguration implements WebMvcConfigurer {
    private final TitleInterceptor titleInterceptor;
    private final LoggedInInterceptor loggedInInterceptor;

    @Autowired
    public ApplicationWebMVCConfiguration(TitleInterceptor titleInterceptor, LoggedInInterceptor loggedInInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.loggedInInterceptor = loggedInInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.titleInterceptor);
        registry.addInterceptor(this.loggedInInterceptor).addPathPatterns("/auth/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
