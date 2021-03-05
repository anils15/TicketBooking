package com.gateway.CommonGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CommonGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonGatewayApplication.class, args);
	}

}
