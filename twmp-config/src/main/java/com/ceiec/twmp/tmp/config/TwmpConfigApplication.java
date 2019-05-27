package com.ceiec.twmp.tmp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class TwmpConfigApplication {


    public static void main(String[] args) {
        SpringApplication.run(TwmpConfigApplication.class, args);
    }

//    /**
//     * refresh project properties
//     *
//     * @return customer properties container
//     */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
//        propertyConfigurer.setIgnoreResourceNotFound(true);
//        Properties properties = PropertyReadUtils.readProjectProperties("application.properties", false);
//        propertyConfigurer.setProperties(properties);
//        return propertyConfigurer;
//    }
}
