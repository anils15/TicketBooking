package com.train.bookingservice.dto;

import com.train.bookingservice.entity.PaymentInformation;

public class TicketBookingAcknowledgement {

	private String status;
	private double totalFare;
	private String pnrNo;
	private PaymentInformation paymentInfomation;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	public PaymentInformation getPaymentInfomation() {
		return paymentInfomation;
	}
	public void setPaymentInfomation(PaymentInformation paymentInfomation) {
		this.paymentInfomation = paymentInfomation;
	}
}
