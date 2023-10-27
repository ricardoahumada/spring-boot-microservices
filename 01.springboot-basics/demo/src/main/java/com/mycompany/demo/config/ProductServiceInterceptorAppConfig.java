package com.mysbapp.demo.config;

import com.mysbapp.demo.interceptor.ProductServiceInterceptor;
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

//        registry.addInterceptor(new UserRoleAuthorizationInterceptor())
//                .excludePathPatterns(
//                        "/products",
//                        "/products/**/authenticate",
//                        "products/**/someresource/verify"
//                ).pathMatcher(new AntPathMatcher());
    }
}