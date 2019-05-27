package com.ceiec.twmp.tmp.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CreateDate：2018/11/7 <br/>
 * Author：wenliang <br/>
 * Description: Zuul main class
 **/

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class TwmpZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwmpZuulApplication.class, args);
    }

    /**
     * RESTful template for ribbon
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * override cross domain filter
     */
    @Bean
    CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //allow cookies cross
        config.addAllowedOrigin("*"); //allow all request url
        config.addAllowedHeader("*"); //allow all request header
        config.addAllowedMethod("*"); //allow all request method(GET,PUT,POST,DELETE,etc)
        config.setMaxAge(1800L); //allow check cache(seconds)
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

//    /**
//     * refresh project properties
//     *
//     * @return customer properties container
//     */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties() {
//        PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
//        propertyConfigurer.setProperties(PropertyReadUtils.readProjectProperties("bootstrap.properties", true));
//        return propertyConfigurer;
//    }
}
