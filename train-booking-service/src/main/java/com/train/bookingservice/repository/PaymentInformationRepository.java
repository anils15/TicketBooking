package com.train.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.train.bookingservice.entity.PaymentInformation;

@Repository
public interface PaymentInformationRepository extends JpaRepository <PaymentInformation, String>{
}
