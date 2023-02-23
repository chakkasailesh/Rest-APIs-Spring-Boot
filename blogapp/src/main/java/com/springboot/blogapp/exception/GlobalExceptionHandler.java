package com.springboot.blogapp.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.blogapp.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundExcepion(ResourceNotFoundException exception,
			WebRequest webRequest) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)),
				HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNameOrEmailExistsException.class)
	public ResponseEntity<ErrorDetails> handleUserNameOrEmailExistsExcepion(UserNameOrEmailExistsException exception,
			WebRequest webRequest) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> handleAccessDeniedExcepion(AccessDeniedException exception,
			WebRequest webRequest) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)),
				HttpStatus.UNAUTHORIZED);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(JWTException.class)
	public ResponseEntity<ErrorDetails> handleJWTExcepion(JWTException exception, WebRequest webRequest) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalExcepion(Exception exception, WebRequest webRequest) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleUsernameNotFoundExcepion(UsernameNotFoundException exception,
			WebRequest webRequest) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false)),
				HttpStatus.UNAUTHORIZED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			errors.put(((FieldError) error).getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
