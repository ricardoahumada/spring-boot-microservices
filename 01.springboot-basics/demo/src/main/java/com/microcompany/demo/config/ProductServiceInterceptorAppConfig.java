package com.microcompany.demo.config;

import com.microcompany.demo.interceptor.ProductServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ProductServiceInterceptorAppConfig implements WebMvcConfigurer {
    @Autowired
    ProductServiceInterceptor productServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(productServiceInterceptor).addPathPatterns("/products/**");
    }
}