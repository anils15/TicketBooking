package com.train.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableMBeanExport;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
public class TrainBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainBookingServiceApplication.class, args);
	}

}
