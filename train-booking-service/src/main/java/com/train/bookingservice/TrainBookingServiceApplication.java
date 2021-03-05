package com.train.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrainBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainBookingServiceApplication.class, args);
	}

}
