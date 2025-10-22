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
            .route( r -> r
                    .path("/api/catalog/content-types/**")
                    .filters(f -> f.rewritePath("/api/catalog/(?<segment>.*)", "/catalog-server/api/${segment}"))
                    .uri("lb://catalog-server")
            )
            .route(r -> r
                    .path("/api/catalog/page-types/**")
                    .filters(f -> f.rewritePath("/api/catalog/(?<segment>.*)", "/catalog-server/api/${segment}"))
                    .uri("lb://catalog-server")
            )
            .route(r -> r
                    .path("/api/user-profiles/**")
                    .filters(f -> f.rewritePath("/api/user-profiles/(?<segment>.*)", "/user-profiles-server/api/user-profiles/${segment}"))
                    .uri("lb://user-profiles-server")
            )
            .route(r -> r
                    .path("/api/images/**")
                    .filters(f -> f.rewritePath("/api/images/(?<segment>.*)", "/images-server/api/images/${segment}"))
                    .uri("lb://images-server")
            )
            .route(r -> r
                    .path("/api/posts/**")
                    .filters(f -> f.rewritePath("/api/posts/(?<segment>.*)", "/posts-server/api/posts/${segment}"))
                    .uri("lb://posts-server")
            )
            .route(r -> r
                    .path("/api/comments/**")
                    .filters(f -> f.rewritePath("/api/comments/(?<segment>.*)", "/posts-server/api/comments/${segment}"))
                    .uri("lb://posts-server")
            )
            .route(r -> r
                   .path("/api/page-profiles/**")
                    .filters(f -> f.rewritePath("/api/page-profiles/(?<segment>.*)", "/page-profiles-server/api/page-profiles/${segment}"))
                   .uri("lb://page-profiles-server")
            )
            .route(r -> r
                .path("/api/results/**")
                .filters(f -> f.rewritePath("/api/results/(?<segment>.*)", "/results-server/api/results/${segment}"))
                .uri("lb://results-server")
            )
            .build();
    }

}
