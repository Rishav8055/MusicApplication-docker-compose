package com.niit.SpringCloudAPIGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p->p
                        .path("/userservice/**")
                        .uri("http://authentication-service:9100/"))
                .route(p->p
                        .path("/usertrack/user/**")
                        .uri("http://track-Service:9500")).build();
    }
}
