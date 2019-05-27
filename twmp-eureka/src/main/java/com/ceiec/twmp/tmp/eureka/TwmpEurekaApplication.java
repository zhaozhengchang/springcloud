package com.ceiec.twmp.tmp.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class
TwmpEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwmpEurekaApplication.class, args);
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
//        propertyConfigurer.setProperties(PropertyReadUtils.readProjectProperties("application.properties", false));
//        return propertyConfigurer;
//    }
}
