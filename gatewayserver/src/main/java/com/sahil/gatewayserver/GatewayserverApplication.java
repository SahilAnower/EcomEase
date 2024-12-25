package com.sahil.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes (RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(r -> r
						.path("/ecomease/products/**")
						.filters(f -> f.rewritePath("/ecomease/products/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", new Date().toString()))
						.uri("lb://PRODUCTS"))
				.route(r -> r
						.path("/ecomease/carts/**")
						.filters(f -> f.rewritePath("/ecomease/carts/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", new Date().toString()))
						.uri("lb://CARTS"))
				.build();
	}

}
