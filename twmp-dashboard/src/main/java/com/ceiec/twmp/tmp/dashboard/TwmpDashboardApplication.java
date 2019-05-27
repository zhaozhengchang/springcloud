package com.ceiec.twmp.tmp.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
public class TwmpDashboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(TwmpDashboardApplication.class, args);
	}
}
