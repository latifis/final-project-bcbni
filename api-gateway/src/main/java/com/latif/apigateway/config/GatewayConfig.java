package com.latif.apigateway.config;

import com.latif.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("users", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("post-service", r -> r.path("/post/**").filters(f -> f.filter(filter)).uri("lb://post-service"))
                .build();
    }

}
