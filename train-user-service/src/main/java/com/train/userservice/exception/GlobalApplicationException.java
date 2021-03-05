package com.train.userservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * @author Anil Pratap Singh
 *
 */
@RestControllerAdvice
public class GlobalApplicationException {

	/**
	 * handleMethodArgumentNotValid is for handling MethodArgumentNotValidException
	 * 
	 * @param exception
	 * @return Map<String, String> that holds error data
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getFieldErrors()
		.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return errors;
	}

	/**
	 * handleMethodArgumentNotValid is for handling HttpMessageNotReadableException
	 * 
	 * @param exception
	 * @return Map<String, String> that holds error data
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, String> handleMethodArgumentNotValid(HttpMessageNotReadableException exception) {
		Map<String, String> errors = new HashMap<>();
		String message;
		Throwable mostSpecificCause = exception.getMostSpecificCause();
		if (mostSpecificCause != null) {
			message = mostSpecificCause.getMessage();
		} else {
			message = exception.getMessage();
		}
		errors.put("Check Field Value", message);
		return errors;
	}
}
