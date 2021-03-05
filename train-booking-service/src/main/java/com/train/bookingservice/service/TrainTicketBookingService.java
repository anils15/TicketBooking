package com.train.bookingservice.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;

import com.train.bookingservice.dto.TicketBookingAcknowledgement;
import com.train.bookingservice.dto.TrainPassengerDetails;
import com.train.bookingservice.dto.TrainTicketBookingRequest;
import com.train.bookingservice.dto.UserAuthenticate;
import com.train.bookingservice.entity.PassengerInfomation;

public interface TrainTicketBookingService {
	
	/**
	 * bookTrainTicket is the method for booking train ticket.
	 * 
	 * @param TrainTicketBookingRequest
	 * @return TrainBookingAcknowledgement
	 */
	public TicketBookingAcknowledgement bookTrainTicket(TrainTicketBookingRequest bookingRequest);

	/**
	 * getPassengerIdDetails is the method to fetch the user details.
	 * 
	 * @param passengerId
	 * @return ResponseEntity<TrainPassengerDetails>
	 */
	public ResponseEntity<TrainPassengerDetails> getPassengerDetails(Long passengerId);

	/**
	 * getPassengerBookingDetails is the method to fetch the passenger booking details.
	 * 
	 * @param passengerId
	 * @return ResponseEntity<PassengerInfomation>
	 */
	public ResponseEntity<PassengerInfomation> getPassengerBookingDetails(@Valid @Min(1) Long passengerId);

	/**
	 * findByUsername is for user authentication.
	 * 
	 * @param userName
	 * @return UserAuthenticate
	 */
	public UserAuthenticate findByUsername(String userName);

	/**
	 * inserUserDetails is for user insertion.
	 * 
	 * @param userData
	 * @return TrainPassengerDetails
	 */
	public TrainPassengerDetails insertUserDetails(UserAuthenticate userData);

}
