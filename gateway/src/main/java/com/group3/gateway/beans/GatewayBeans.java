package com.group3.gateway.beans;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeans {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r
                    .path("/api/users/**")
                    .filters(f -> f.rewritePath("/api/users/(?<segment>.*)", "/users-server/api/users/${segment}"))
                    .uri("lb://users-server")
            )
            .route(r -> r
                    .path("/api/auth/**")
                    .filters(f -> f.rewritePath("/api/auth/(?<segment>.*)", "/users-server/api/users/${segment}"))
                    .uri("lb://users-server")
            )
            .route(r -> r
                    .path("/api/catalog/**")
                    .filters(f -> f.rewritePath("/api/catalog/(?<segment>.*)", "/catalog-server/api/catalog/${segment}"))
                    .uri("lb://catalog-server")
            )
            .build();
    }

}
