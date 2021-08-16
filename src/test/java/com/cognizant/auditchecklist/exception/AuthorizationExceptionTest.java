package com.cognizant.auditchecklist.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class AuthorizationExceptionTest {

	/*
	 * Tests for AuthorizationException
	 * 
	 */
	@Test
	void AuthorizationExceptionTests() {
		log.info("Inside AuthorizationExceptionTest");
		AuthorizationException authE = new AuthorizationException("Auth Exception");
		assertEquals("Auth Exception", authE.getMessage());
		log.info("End AuthorizationExceptionTest");
	}

}
