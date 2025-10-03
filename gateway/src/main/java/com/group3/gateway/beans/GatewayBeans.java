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
                    .filters(f -> f.rewritePath("/api/auth/(?<segment>.*)", "/users-server/api/auth/${segment}"))
                    .uri("lb://users-server")
            )
            .route(r -> r
                    .path("/api/catalog/styles/**")
                    .filters(f -> f.rewritePath("/api/catalog/(?<segment>.*)", "/catalog-server/api/${segment}"))
                    .uri("lb://catalog-server")
            )
            .route(r -> r
                    .path("/api/catalog/instruments/**")
                    .filters(f -> f.rewritePath("/api/catalog/(?<segment>.*)", "/catalog-server/api/${segment}"))
                    .uri("lb://catalog-server")
            )
            .route(r -> r
                    .path("/api/catalog/categories/**")
                    .filters(f -> f.rewritePath("/api/catalog/(?<segment>.*)", "/catalog-server/api/${segment}"))
                    .uri("lb://catalog-server")
            )
            .route(r -> r
                    .path("/api/catalog/page-types/**")
                    .filters(f -> f.rewritePath("/api/catalog/(?<segment>.*)", "/catalog-server/api/${segment}"))
                    .uri("lb://catalog-server")
            )
            .route(r -> r
                    .path("/api/profiles/**")
                    .filters(f -> f.rewritePath("/api/profiles/(?<segment>.*)", "/profiles-server/api/profiles/${segment}"))
                    .uri("lb://profiles-server")
            )
            .route(r -> r
                    .path("/api/images/**")
                    .filters(f -> f.rewritePath("/api/images/(?<segment>.*)", "/images-server/api/images/${segment}"))
                    .uri("lb://images-server")
            )
            .route(r -> r
                    .path("/api/posts/**")
                    .filters(f -> f.rewritePath("/api/posts/(?<segment>.*)", "/posts-server/api/${segment}"))
                    .uri("lb://posts-server")
            )
            .route(r -> r
                    .path("/api/comments/**")
                    .filters(f -> f.rewritePath("/api/comments/(?<segment>.*)", "/posts-server/api/${segment}"))
                    .uri("lb://posts-server")
            )
            .route(r -> r
                   .path("/api/pages/**")
                   .filters(f -> f.rewritePath("/api/pages/(?<segment>.*)", "/pages-server/api/pages/${segment}"))
                   .uri("lb://pages-server")
            )
            .build();
    }

}
