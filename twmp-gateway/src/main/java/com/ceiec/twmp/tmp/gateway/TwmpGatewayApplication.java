package com.ceiec.twmp.tmp.gateway;

import com.ceiec.twmp.tmp.gateway.filters.post.LoginLogoutFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


/*************************************************************************************************************************************
 ** @Description spring gateway
 ** @Author Ding
 ** @Date 2019/3/7 17:00
 **
 ************************************************************************************************************************************/
@SpringBootApplication
public class TwmpGatewayApplication {

    private static final String ALL = "*";
    private static final String MAX_AGE = "18000L";


    public static void main(String[] args) {
        SpringApplication.run(TwmpGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator authorizeLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route("authorize",r -> r.path("/authorize/**")  //authorize  route
                        .filters(f -> f.stripPrefix(1).filter(new LoginLogoutFilter()))
                        .uri("lb://twmp-authorize"))
                .route("ef",r -> r.path("/ef/**")  //ef business route
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://twmp-tmp"))
//                .route("websocket",r -> r.path("/twmp-tmp/info/**") //websocket stomp connect route
//                        .uri("lb://twmp-tmp"))
//                .route("websocket",r -> r.path("/twmp-tmp/**")//websocket stomp transfer route
//                        .uri("lb:ws://twmp-tmp"))
                .route("websocket",r -> r.path("/websocket/message") // websocket origin
                           .uri("lb://twmp-tmp"))
                .build();
    }


    /*************************************************************************************************************************************
     ** @Description 解决跨域问题
     ** @param:
     ** @Return org.springframework.web.server.WebFilter
     ** @Author Ding
     ** @Date 2019/3/22 14:45
     **
     ************************************************************************************************************************************/
    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (!CorsUtils.isCorsRequest(request)) {
                return chain.filter(ctx);
            }
            HttpHeaders requestHeaders = request.getHeaders();
            ServerHttpResponse response = ctx.getResponse();
            HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
            HttpHeaders headers = response.getHeaders();
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
            headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
            if (requestMethod != null) {
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
            }
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ALL);
            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
            return chain.filter(ctx);
        };

    }


}
