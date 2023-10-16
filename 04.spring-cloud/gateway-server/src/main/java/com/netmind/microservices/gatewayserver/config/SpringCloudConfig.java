package com.netmind.microservices.gatewayserver.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

// TODO: uncomment for routes
@Configuration
public class SpringCloudConfig {
    // TODO: uncomment for global filter
     @Bean
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            System.out.println("Global filter 1");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("Global filter 2");
            }));
        };
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Open Api
                // TODO: uncomment for api docs
                .route(r -> r.path("/products-service/v3/api-docs")
                        .filters(f->f.rewritePath("/products-service/(?<path>.*)","/${path}"))
                        .uri("lb://products-service"))
                .route(r -> r.path("/orders-service/v3/api-docs")
                        .filters(f->f.rewritePath("/orders-service/v3/api-docs","/v3/api-docs"))
                        .uri("lb://orders-service"))
                // Endpoints
                // TODO: uncomment for routes
                .route(r -> r.path("/orders/**")
                        .uri("lb://orders-service/"))
                .route(r -> r.path("/products/**")
                        .filters(f ->
                                f.addRequestHeader("added-request-header", "added-request-header-value")
                                        .addResponseHeader("added-response-header", "added-response-header-value")
                        ).uri("lb://products-service/"))
                .build();
    }

}