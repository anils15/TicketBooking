package com.train.bookingservice.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class TrainTicketBookingRequest {
	
	@Positive(message = "Passanger ID is unique, So it should be positive")
    @Min(value = 1, message = "Kindly enter ID greater than 0")
	private Long passengerId;
	
	@NotEmpty(message = "Please enter the passanger name")
	@NotNull
	@Column(length=30)
	private String passengerName;
	
	@NotEmpty(message = "Please enter the source location")
	@NotNull
	@Column(length=30)
	private String source;
	
	@NotEmpty(message = "Please enter the destination location")
	@NotNull
	@Column(length=30)
    private String destination;
	
	@Email
    private String email;
	
	@NotNull
	@FutureOrPresent
	@Temporal(TemporalType.TIMESTAMP)
    private Date travelDate;
	
	@Positive
	@Min(value = 1, message = "Seat selection should be greater than 1")
    private Long seats;
   
	@NotEmpty(message = "Please enter the valid account number")
	@NotNull
	@Column(length=30)
    private String accountNo;
    
    @Positive(message = "Amount should be positive")
    @Min(value = 0, message = "Amount should be greater than 1")
    private double amount;
    
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public double getSeats() {
		return seats;
	}
	public void setSeats(Long seats) {
		this.seats = seats;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}
}
