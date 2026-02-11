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
                // USERS SERVER
                .route(r -> r
                        .path("/api/users/**")
                        .filters(f -> f.rewritePath("/api/users(?<segment>/.*)?", "/users-server/api/users${segment}"))
                        .uri("lb://users-server")
                )
                .route(r -> r
                        .path("/api/auth/**")
                        .filters(f -> f.rewritePath("/api/auth(?<segment>/.*)?", "/users-server/api/auth${segment}"))
                        .uri("lb://users-server")
                )
                .route(r -> r
                        .path("/api/follows/**")
                        .filters(f -> f.rewritePath("/api/follows(?<segment>/.*)?", "/users-server/api/follows${segment}"))
                        .uri("lb://users-server")
                )
                .route(r -> r
                        .path("/api/banned/**")
                        .filters(f -> f.rewritePath("/api/banned(?<segment>/.*)?", "/users-server/api/banned${segment}"))
                        .uri("lb://users-server")
                )
                .route(r -> r
                        .path("/api/reviews/**")
                        .filters(f -> f.rewritePath("/api/reviews(?<segment>/.*)?", "/users-server/api/reviews${segment}"))
                        .uri("lb://users-server")
                )

                // CATALOG SERVER
                .route(r -> r
                        .path("/api/catalog/**")
                        .filters(f -> f.rewritePath("/api/catalog/(?<segment>.*)", "/catalog-server/api/${segment}"))
                        .uri("lb://catalog-server")
                )

                // IMAGES SERVER
                .route(r -> r
                        .path("/api/images/**")
                        .filters(f -> f.rewritePath("/api/images(?<segment>/.*)?", "/images-server/api/images${segment}"))
                        .uri("lb://images-server")
                )

                // POSTS SERVER
                .route(r -> r
                        .path("/api/posts/**")
                        .filters(f -> f.rewritePath("/api/posts(?<segment>/.*)?", "/posts-server/api/posts${segment}"))
                        .uri("lb://posts-server")
                )
                .route(r -> r
                        .path("/api/comments/**")
                        .filters(f -> f.rewritePath("/api/comments(?<segment>/.*)?", "/posts-server/api/comments${segment}"))
                        .uri("lb://posts-server")
                )

                // PAGE PROFILES SERVER
                .route(r -> r
                        .path("/api/page-profiles/**")
                        .filters(f -> f.rewritePath("/api/page-profiles(?<segment>/.*)?", "/page-profiles-server/api/page-profiles${segment}"))
                        .uri("lb://page-profiles-server")
                )

                // RESULTS SERVER
                .route(r -> r
                        .path("/api/results/**")
                        .filters(f -> f.rewritePath("/api/results(?<segment>/.*)?", "/results-server/api/results${segment}"))
                        .uri("lb://results-server")
                )

                // EVENTS SERVER
                .route(r -> r
                        .path("/api/events/**")
                        .filters(f -> f.rewritePath("/api/events(?<segment>/.*)?", "/events-server/api/events${segment}"))
                        .uri("lb://events-server")
                )

                // NOTIFICATION SERVER
                .route(r -> r
                        .path("/api/notifications/**")
                        .filters(f -> f.rewritePath("/api/notifications(?<segment>/.*)?", "/notification-server/api/notifications${segment}"))
                        .uri("lb://notification-server")
                )

                // CHAT SERVER
                .route(r -> r
                        .path("/api/chat/**")
                        .filters(f -> f.rewritePath("/api/chat(?<segment>/.*)?", "/chat-server/api/chat${segment}"))
                        .uri("lb://chat-server")
                )

                .build();
    }

}