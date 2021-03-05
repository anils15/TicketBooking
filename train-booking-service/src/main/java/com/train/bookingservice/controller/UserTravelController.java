package com.train.bookingservice.controller;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.bookingservice.dto.TicketBookingAcknowledgement;
import com.train.bookingservice.dto.TrainPassengerDetails;
import com.train.bookingservice.dto.TrainTicketBookingRequest;
import com.train.bookingservice.dto.UserAuthenticate;
import com.train.bookingservice.entity.PassengerInfomation;
import com.train.bookingservice.exception.InsufficientAmountException;
import com.train.bookingservice.service.TrainTicketBookingService;


/**
 * 
 * 
 * @author Anil Pratap Singh
 *
 */
@RestController
public class UserTravelController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserTravelController.class);
	
	@Autowired
	TrainTicketBookingService trainTicketBookingService;
	
	/**
	 * bookTrainTicket is the method for booking train ticket.
	 * 
	 * @param TrainTicketBookingRequest
	 * @return ResponseEntity<TrainBookingAcknowledgement>
	 */
	@PostMapping("/ticket")
	public ResponseEntity<TicketBookingAcknowledgement> bookTrainTicket(@Valid @RequestBody TrainTicketBookingRequest bookingRequest) throws InsufficientAmountException{
		logger.info("Transaction about to start");
		TicketBookingAcknowledgement returnStatus = new TicketBookingAcknowledgement();
		try {
			returnStatus = trainTicketBookingService.bookTrainTicket(bookingRequest);
			if(Objects.nonNull(returnStatus)) {
				return new ResponseEntity<>(returnStatus, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(returnStatus, HttpStatus.BAD_REQUEST);
			}
		}catch(InsufficientAmountException exception){
			returnStatus.setStatus("Insufficient Fund");
			return new ResponseEntity<>(returnStatus, HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * getPassengerIdDetails is the method to fetch the user details.
	 * 
	 * @param passengerId
	 * @return ResponseEntity<TrainPassangerDetails>
	 */
	@GetMapping("/passenger")
	public ResponseEntity<TrainPassengerDetails> getPassengerDetails(@Valid @Min(value = 1) @RequestParam Long passengerId){
		logger.info("User details in booking service");
		return trainTicketBookingService.getPassengerDetails(passengerId);
	}
	
	/**
	 * getPassengerBookingDetails is the method to fetch the passenger booking details.
	 * 
	 * @param passengerId
	 * @return ResponseEntity<PassengerInfomation>
	 */
	@GetMapping("/passengerBookings")
	public ResponseEntity<PassengerInfomation> getPassengerBookingDetails(@Valid @Min(value = 1) @RequestParam Long passengerId){
		logger.info("passenger booking details is about to fetch");
		return trainTicketBookingService.getPassengerBookingDetails(passengerId);
	}
	
	/**
	 * findByUsername is for user authentication.
	 * 
	 * @param userName
	 * @return UserAuthenticate
	 */
	@GetMapping("/user")
	public UserAuthenticate findByUsername(@RequestParam String userName) {
		return trainTicketBookingService.findByUsername(userName);
	}
	
	/**
	 * inserUserDetails is for user insertion.
	 * 
	 * @param userData
	 * @return TrainPassengerDetails
	 */
	@PostMapping("/user")
	public TrainPassengerDetails insertUserDetails(@Valid @RequestBody UserAuthenticate userData) {
		return trainTicketBookingService.insertUserDetails(userData);
	}
}
