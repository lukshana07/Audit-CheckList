package com.cognizant.auditchecklist.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class ExceptionDetailsTest{

	@Mock
	ExceptionDetails exceptionDetail;

	/*
	 * Function to run before each @Test
	 */
	@BeforeEach
	void exceptionDetails_SettingValues() {
		log.info("Inside exceptionDetails_SettingValues()");
		exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.FORBIDDEN, "ExceptionDetailsTest");
		log.info("End exceptionDetails_SettingValues()");
	}

	/*
	 * Testing for AllArgsConstructor and Getters
	 */
	@Test
	void exceptionDetails_AllArgsConstructor() {
		log.info("Inside exceptionDetails_AllArgsConstructor()");
		assertEquals("ExceptionDetailsTest", exceptionDetail.getMessage());
		assertEquals(LocalDateTime.now().getMonth(), exceptionDetail.getTimeStamp().getMonth());
		assertEquals(HttpStatus.FORBIDDEN, exceptionDetail.getStatus());
		log.info("End ExceptionDetailsTest()");
	}

}
