package com.train.bookingservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.train.bookingservice.entity.PassengerInfomation;

@Repository
public interface PassengerInformationRepository extends JpaRepository <PassengerInfomation, Long>{

	Optional<PassengerInfomation> findByPassengerName(String userName);

	Optional<PassengerInfomation> findByPassengerIdAndPassengerName(Long passengerId, String passengerName);

}
