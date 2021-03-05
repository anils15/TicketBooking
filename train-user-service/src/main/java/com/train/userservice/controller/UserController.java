package com.train.userservice.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.userservice.dto.TrainPassengerDetails;
import com.train.userservice.service.UserService;


/**
 * 
 * @author Anil Pratap Singh
 *
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * getPassengerIdDetails is the method to fetch the user details.
	 * 
	 * @param passengerId
	 * @return ResponseEntity<List<CustomerStatementDetailDto>>
	 */
	@GetMapping("/passengerDetail")
	public ResponseEntity<TrainPassengerDetails> getPassengerIdDetails(@Valid @Min(value = 1) @RequestParam Long passengerId) {
		logger.info("Fetching user details");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("passengerDetails");
		return circuitBreaker.run(() -> userservice.getPassengerDetails(passengerId), Throwable -> getThrowableMessage());
//		return userservice.getPassengerDetails(passengerId);
	}
	
	public ResponseEntity<TrainPassengerDetails> getThrowableMessage() {
		TrainPassengerDetails passengerDetails = new TrainPassengerDetails();
		passengerDetails.setUserStatus("User Service is dowm, Please try after some time");
		return new ResponseEntity<>(passengerDetails, HttpStatus.OK);
	}
}
