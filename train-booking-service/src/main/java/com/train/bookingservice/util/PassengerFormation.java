package com.train.bookingservice.util;

import org.springframework.beans.BeanUtils;

import com.train.bookingservice.dto.TrainTicketBookingRequest;
import com.train.bookingservice.entity.PassengerInfomation;

/**
 * PassengerFormation is the entity formation
 * @author Anil Pratap Singh
 *
 */
public class PassengerFormation {

	public static PassengerInfomation formPassengerInfo(TrainTicketBookingRequest bookingRequest) {
		PassengerInfomation passengerInfo = new PassengerInfomation();
		BeanUtils.copyProperties(bookingRequest, passengerInfo);
		return passengerInfo;
	}

}
