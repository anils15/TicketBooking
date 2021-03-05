package com.train.bookingservice.exception;

public class InsufficientAmountException extends RuntimeException {

    /**
	 * Initial serial version
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientAmountException(String msg){
        super(msg);
    }
}
