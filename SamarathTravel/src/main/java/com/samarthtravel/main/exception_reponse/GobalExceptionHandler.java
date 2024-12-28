package com.samarthtravel.main.exception_reponse;

import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.samarthtravel.main.exception.BusIsNotAvailableException;
import com.samarthtravel.main.exception.SeatAllReadyBookedException;



@RestControllerAdvice
public class GobalExceptionHandler {
	
	@ExceptionHandler(SeatAllReadyBookedException.class)
	public ResponseEntity<String> handleSeatIsAlreadyBooked(SeatAllReadyBookedException seat)
	{
		return new ResponseEntity<>(seat.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(BusIsNotAvailableException.class)
	 public ResponseEntity<String> handleBusNotAvailableException(BusIsNotAvailableException ex) 
	 {
	     return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	 }
	

	   

	   
	    
}
