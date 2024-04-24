package com.ybardockz.streaming_music.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ybardockz.streaming_music.services.exceptions.DbException;
import com.ybardockz.streaming_music.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		
		err.setError(e.getMessage());
		err.setPath(request.getRequestURI());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setTimestamp(Instant.now());
		
		return ResponseEntity.status(HttpStatus.valueOf(err.getStatus())).body(err);
	
	}
	
	@ExceptionHandler(DbException.class)
	public ResponseEntity<StandardError> dbIntegrityException(DbException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		
		err.setError(e.getMessage());
		err.setPath(request.getRequestURI());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setTimestamp(Instant.now());
		
		return ResponseEntity.status(HttpStatus.valueOf(err.getStatus())).body(err);
		
		
	}

}
