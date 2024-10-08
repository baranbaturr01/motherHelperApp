package com.baranbatur.newMotherHelper.config;

import com.baranbatur.newMotherHelper.interceptors.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry register) {
        register.addInterceptor(jwtInterceptor).addPathPatterns("/api/v1/user-category-list");
        register.addInterceptor(jwtInterceptor).addPathPatterns("/api/v1/user-category-list/*");
        register.addInterceptor(jwtInterceptor).addPathPatterns("/api/v1/category-list/find-by-category-id");
        register.addInterceptor(jwtInterceptor).addPathPatterns("/api/v1/blog-posts/*");
        register.addInterceptor(jwtInterceptor).addPathPatterns("/api/v1/comments/*");
        register.addInterceptor(jwtInterceptor).addPathPatterns("/api/v1/user/delete");
    }
}
