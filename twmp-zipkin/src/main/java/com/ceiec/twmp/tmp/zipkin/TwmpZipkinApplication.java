package com.ceiec.twmp.tmp.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.internal.EnableZipkinServer;

@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication
public class TwmpZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwmpZipkinApplication.class, args);
    }

//    /**
//     * refresh project properties
//     *
//     * @return customer properties container
//     */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties() {
//        PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
//        propertyConfigurer.setIgnoreResourceNotFound(true);
//        propertyConfigurer.setProperties(PropertyReadUtils.readProjectProperties("bootstrap.properties", true));
//        return propertyConfigurer;
//    }
}
