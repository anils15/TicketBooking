package com.train.bookingservice.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class UserAuthenticate {

	@NotEmpty(message = "Please enter the passanger name")
	@NotNull
	@Column(length=30)
	private String passengerName;
	
	@Email
	private String email;
	
	@NotEmpty(message = "Please enter the password")
	@NotNull
	@Column(length=30)
	private String password;

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
