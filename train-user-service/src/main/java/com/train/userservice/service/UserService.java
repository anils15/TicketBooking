package com.train.userservice.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.train.userservice.dto.TrainPassengerDetails;

@FeignClient(name = "http://booking-service/book")
public interface UserService {

	@GetMapping("/passengerDetail")
	public ResponseEntity<TrainPassengerDetails> getPassengerDetails(@Valid @Min(value = 1) @RequestParam Long passengerId);

}
