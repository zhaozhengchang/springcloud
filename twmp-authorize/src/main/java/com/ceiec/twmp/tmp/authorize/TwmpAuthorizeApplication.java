package com.ceiec.twmp.tmp.authorize;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
public class TwmpAuthorizeApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(TwmpAuthorizeApplication.class, args);
        }catch (Exception e){
        }
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
     * Get servlet servlet registration bean  For Hystrix Dashboard
     *
     * @return the servlet registration bean
     */
    @Bean
    public ServletRegistrationBean getServlet(){

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);

        registrationBean.setLoadOnStartup(1);

        registrationBean.addUrlMappings("/actuator/hystrix.stream");

        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
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
