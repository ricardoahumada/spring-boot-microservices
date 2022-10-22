package com.netmind.microservices.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/currency-converter/**")
                        .uri("lb://currency-conversion-service/currency-converter-feign/"))

                .route(r -> r.path("/currency-exchange/**")
                        .uri("lb://currency-exchange-service/currency-exchange/"))

                .build();
    }

}