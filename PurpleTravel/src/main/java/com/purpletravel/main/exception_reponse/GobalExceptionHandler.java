package com.purpletravel.main.exception_reponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.purpletravel.main.exception.BusNotAvailableException;
import com.purpletravel.main.exception.SeatNotFoundException;

@RestControllerAdvice
public class GobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException m)
	{
		Map<String,String> errors  = new HashMap<>();
		m.getBindingResult().getFieldErrors().forEach(error->{
		   String field   =  error.getField(); 
		   String message  =  error.getDefaultMessage();
		   
		   errors.put(field, message);
		});
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BusNotAvailableException.class)
	public ResponseEntity<String> handleBusNotAvailableException(BusNotAvailableException b)
	{
		return new ResponseEntity<>(b.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> handleSeatNotFoundException(SeatNotFoundException s)
	{
		return new ResponseEntity<String>(s.getMessage(),HttpStatus.NOT_FOUND);
	}
}
