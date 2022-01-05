package com.latif.apigateway.configuration;

import com.latif.apigateway.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfiguration {
    private final JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("post", r -> r.path("/posts/**").filters(f -> f.filter(filter)).uri("lb://post-service"))
                .route("user", r->r.path("/users/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("login", r->r.path("/login/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("register", r->r.path("/register/**").filters(f -> f.filter(filter)).uri("lb://user-service"))
                .route("log", r -> r.path("/logs/**").filters(f -> f.filter(filter)).uri("lb://log-service"))
                .build();
    }
}
