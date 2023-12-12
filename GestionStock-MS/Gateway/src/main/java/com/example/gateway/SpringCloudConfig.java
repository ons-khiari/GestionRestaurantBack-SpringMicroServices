package com.example.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {



    @Bean
    public RouteLocator gatewayRoutes( RouteLocatorBuilder builder) {

        return builder.routes()

                .route("ArticleMS", r->r.path("/articles/**")
                        .uri("http://article-ms:9098/"))
                .route("categorieMS", r->r.path("/Categories/**")
                        .uri("http://categorie-ms:9099/"))
                .route("crudNodeMongoMS", r->r.path("/livraison/**")
                        .uri("http://livraison-node-ms:4001/"))
                .route("client-service", r->r.path("/clients/**")
                        .uri("http://client-ms:8082/"))
                .route("fournisseur-service", r->r.path("/fournisseur/**")
                        .uri("http://fournisseur-ms:8083/"))
                .build();
    }
}