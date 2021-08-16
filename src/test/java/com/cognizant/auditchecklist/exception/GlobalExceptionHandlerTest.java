package com.cognizant.auditchecklist.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	@Mock
	ExceptionDetails customErrorResponse;

	@BeforeEach
	public void setUp() {
		customErrorResponse = new ExceptionDetails(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, "test message");
		globalExceptionHandler = new GlobalExceptionHandler();
	}

	@Test
	public void handlesAuthorizationExceptionTest() {
		AuthorizationException e = new AuthorizationException("message");
		globalExceptionHandler.handleAuthorizationException(e);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.FORBIDDEN);
		assertEquals(403, entity.getStatusCodeValue());
	}

	@Test
	public void handlesAuditCheckListNotFoundExceptionTest() {
		AuditCheckListNotFoundException e = new AuditCheckListNotFoundException("message");
		globalExceptionHandler.handleCheckListNotFoundException(e);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
		assertEquals(404, entity.getStatusCodeValue());
	}

	@Test
	public void handlesMissingRequestHeaderExceptionTest() {

		Exception e = new Exception("message");
		globalExceptionHandler.handleMissingRequestHeaderException(e);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
		assertEquals(400, entity.getStatusCodeValue());
	}

}
