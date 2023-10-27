package com.microcompany.demo.config;

import com.microcompany.demo.interceptor.ProductServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Component
public class ProductServiceInterceptorAppConfig implements WebMvcConfigurer {
    @Autowired
    ProductServiceInterceptor productServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(productServiceInterceptor).addPathPatterns("/products/**");

//        registry.addInterceptor(new UserRoleAuthorizationInterceptor())
//                .excludePathPatterns(
//                        "/products",
//                        "/products/**/authenticate",
//                        "products/**/someresource/verify"
//                ).pathMatcher(new AntPathMatcher());
    }
}