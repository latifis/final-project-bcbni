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
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("bank-account", r -> r.path("/bank-account/**").filters(f -> f.filter(filter)).uri("lb://bank" +
                        "-account-service"))
                .route("order-service", r -> r.path("/order/**").filters(f -> f.filter(filter)).uri("lb://order" +
                        "-service")).build();
    }

}
