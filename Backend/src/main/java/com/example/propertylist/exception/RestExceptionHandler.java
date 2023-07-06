package com.example.propertylist.exception;

import com.sun.media.sound.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorResponse> handler(InvalidDataException ex) {
		ErrorResponse exception = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(exception,
				HttpStatus.BAD_REQUEST);
		return response;
	}
}
