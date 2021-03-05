package com.train.bookingservice.util;

import java.util.HashMap;
import java.util.Map;

import com.train.bookingservice.exception.InsufficientAmountException;

/**
 * PaymentUtils holds account and amount details.
 * @author Anil Pratap Singh
 *
 */
public class PaymentUtils {
	
	 private static Map<String, Double> paymentMap = new HashMap<>();
	    static {
	        paymentMap.put("Acc101", 12000.0);
	        paymentMap.put("Acc102", 10000.0);
	        paymentMap.put("Acc102", 5000.0);
	        paymentMap.put("Acc104", 8000.0);
	        paymentMap.put("Acc105", 800.0);
	    }

	    public static boolean validateCreditLimit(String accNo, double paidAmount) {
	    	if(paymentMap.getOrDefault(accNo, 0.0) == 0.0) {
	    		return true;
	    	}
	        if (paidAmount > paymentMap.get(accNo)) {
	            throw new InsufficientAmountException("Insufficient fund in the account");
	        } else {
	            return false;
	        }
	    }
}
