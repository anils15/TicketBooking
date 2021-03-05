package com.train.bookingservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PASSENGER_INFO")
public class PassengerInfomation {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long passengerId;
    private String passengerName;
    private String passengerPassword;
    private String email;
    @OneToMany(targetEntity = PaymentInformation.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_payment", referencedColumnName = "passengerId")
    private List<PaymentInformation> passengerPayments;
    
	public Long getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}
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
	public List<PaymentInformation> getPassengerPayments() {
		return passengerPayments;
	}
	public void setPassengerPayments(List<PaymentInformation> passengerPayments) {
		this.passengerPayments = passengerPayments;
	}
	public String getPassengerPassword() {
		return passengerPassword;
	}
	public void setPassengerPassword(String passengerPassword) {
		this.passengerPassword = passengerPassword;
	}
}
