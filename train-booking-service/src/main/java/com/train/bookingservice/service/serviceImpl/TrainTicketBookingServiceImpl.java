package com.train.bookingservice.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.train.bookingservice.dto.TicketBookingAcknowledgement;
import com.train.bookingservice.dto.TrainPassengerDetails;
import com.train.bookingservice.dto.TrainTicketBookingRequest;
import com.train.bookingservice.dto.UserAuthenticate;
import com.train.bookingservice.entity.PassengerInfomation;
import com.train.bookingservice.entity.PaymentInformation;
import com.train.bookingservice.repository.PassengerInformationRepository;
import com.train.bookingservice.repository.PaymentInformationRepository;
import com.train.bookingservice.service.TrainTicketBookingService;
import com.train.bookingservice.util.PassengerFormation;
import com.train.bookingservice.util.PaymentUtils;

@Service
public class TrainTicketBookingServiceImpl implements TrainTicketBookingService{

	@Autowired
	private PassengerInformationRepository passengerInformationRepository;
	@Autowired
	private PaymentInformationRepository paymentInformationRepository;

	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public TicketBookingAcknowledgement bookTrainTicket(TrainTicketBookingRequest bookingRequest) {

		if(PaymentUtils.validateCreditLimit(bookingRequest.getAccountNo(), bookingRequest.getAmount())) {
			TicketBookingAcknowledgement bookingAcknowledgement = new TicketBookingAcknowledgement();
			bookingAcknowledgement.setStatus("Enter valid account details");
			return bookingAcknowledgement;
		}

		Optional<PassengerInfomation> passengerDetail = passengerInformationRepository.findByPassengerIdAndPassengerName(bookingRequest.getPassengerId(), bookingRequest.getPassengerName());
		if(!passengerDetail.isPresent()) {
			TicketBookingAcknowledgement bookingAcknowledgement = new TicketBookingAcknowledgement();
			bookingAcknowledgement.setStatus("UserId and UserName mismatch... Kindly check and continue");
			return bookingAcknowledgement;
		}

		PaymentInformation paymentInfo = new PaymentInformation();
		BeanUtils.copyProperties(bookingRequest, paymentInfo);
		paymentInfo.setCardType("CREDIT");
		Random randomValue = new Random();
		int randomNumber = 100000 + randomValue.nextInt(900000);
		paymentInfo.setPnrNo(String.valueOf(randomNumber));
		paymentInformationRepository.save(paymentInfo);

		Optional<PassengerInfomation> passengerIdValue = passengerInformationRepository.findById(bookingRequest.getPassengerId());
		PassengerInfomation passengerInfo = PassengerFormation.formPassengerInfo(bookingRequest);
		if(passengerIdValue.isPresent()) {
			List<PaymentInformation> dataList = passengerIdValue.get().getPassengerPayments();
			dataList.add(paymentInfo);
			passengerInfo.setPassengerPassword(passengerIdValue.get().getPassengerPassword());
			passengerInfo.setPassengerPayments(dataList);
			passengerInformationRepository.save(passengerInfo);
		}else {
			List<PaymentInformation> dataList = new ArrayList<>();
			dataList.add(paymentInfo);
			passengerInfo.setPassengerPayments(dataList);
			passengerInformationRepository.save(passengerInfo);
		}

		TicketBookingAcknowledgement bookingAcknowledgement = new TicketBookingAcknowledgement();
		bookingAcknowledgement.setStatus("Booking Successfull");
		bookingAcknowledgement.setTotalFare(paymentInfo.getAmount());
		bookingAcknowledgement.setPnrNo(paymentInfo.getPnrNo());
		bookingAcknowledgement.setPaymentInfomation(paymentInfo);

		return bookingAcknowledgement;
	}

	@Override
	public ResponseEntity<TrainPassengerDetails> getPassengerDetails(Long passengerId) {
		Optional<PassengerInfomation> passengerDetail = passengerInformationRepository.findById(passengerId);
		TrainPassengerDetails passangerData = new TrainPassengerDetails();
		if(passengerDetail.isPresent()) {
			BeanUtils.copyProperties(passengerDetail.get(), passangerData);
			passangerData.setUserStatus("Passanger details found");
			return new ResponseEntity<>(passangerData, HttpStatus.OK);
		}else {
			passangerData.setUserStatus("Passanger details not found");
			return new ResponseEntity<>(passangerData, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<PassengerInfomation> getPassengerBookingDetails(@Valid @Min(1) Long passengerId) {
		Optional<PassengerInfomation> passengerDetail = passengerInformationRepository.findById(passengerId);
		if(passengerDetail.isPresent()) {
			return new ResponseEntity<>(passengerDetail.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new PassengerInfomation(), HttpStatus.OK);
		}
	}

	@Override
	public UserAuthenticate findByUsername(String userName) {
		Optional<PassengerInfomation> passengerDetail = passengerInformationRepository.findByPassengerName(userName);
		UserAuthenticate userData = new UserAuthenticate();
		if(passengerDetail.isPresent()) {
			BeanUtils.copyProperties(passengerDetail.get(), userData);
			userData.setPassword(passengerDetail.get().getPassengerPassword());
			return userData;
		}else {
			return userData;
		}
	}

	@Override
	public TrainPassengerDetails insertUserDetails(UserAuthenticate userData) {
		Optional<PassengerInfomation> passengerDetail = passengerInformationRepository.findByPassengerName(userData.getPassengerName());
		TrainPassengerDetails passengerDetails = new TrainPassengerDetails();
		if(passengerDetail.isPresent()) {
			passengerDetails.setUserStatus("User already present");
			return passengerDetails;
		}else {
			PassengerInfomation newPassenger = new PassengerInfomation();
			BeanUtils.copyProperties(userData, newPassenger);
			newPassenger.setPassengerPassword(userData.getPassword());
			newPassenger.setPassengerPayments(new ArrayList<>());
			BeanUtils.copyProperties(userData, passengerDetails);
			passengerInformationRepository.save(newPassenger);
			passengerDetails.setUserStatus("User Added");
			return passengerDetails;
		}
	}

}
