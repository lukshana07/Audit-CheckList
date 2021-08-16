package com.cognizant.auditchecklist.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration 
class AuditCheckListNotFoundExceptionTest {

	
	@Mock
	AuditCheckListNotFoundException auditCheckListnotFoundException;
	
	@Test
	public void contextLoads() {
		assertNotNull(auditCheckListnotFoundException);
	}
	
	@Test 
	public void testConstructor() {
		assertNotNull(new AuditCheckListNotFoundException("Exception"));
	}


}
