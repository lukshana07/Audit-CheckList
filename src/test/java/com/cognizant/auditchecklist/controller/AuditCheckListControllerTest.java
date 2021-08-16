
package com.cognizant.auditchecklist.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.auditchecklist.exception.AuthorizationException;
import com.cognizant.auditchecklist.feign.AuthorisingClient;
import com.cognizant.auditchecklist.model.AuditQuestion;
import com.cognizant.auditchecklist.repository.AuditQuestionRepo;
import com.cognizant.auditchecklist.service.AuditCheckListServiceImplementation;

/**
 *  * This class contains test cases for the AuthController  *          class
 * which are written using junit and mockito  
 */
@SpringBootTest
@ContextConfiguration
public class AuditCheckListControllerTest {

// @Mock
// AuthClient authClient;

	@Mock
	AuthorisingClient tokenService;

// @Mock
// AuditType auditType;

	@Mock
	AuditCheckListServiceImplementation questionsService;

	@Mock
	Environment env;

	@InjectMocks
	AuditCheckListController auditCheckListController;

	@Mock
	AuditQuestionRepo questionsRepository;

	@Test
	public void contextLoads() {
		assertNotNull(auditCheckListController);
	}

	/**
	 * To test testGetCheckList method
	 */
	@Test
	public void testgetQuestions() {
		ResponseEntity<?> responseEntity = null;
		List<AuditQuestion> questionsList = new ArrayList<AuditQuestion>();
		questionsList.add(new AuditQuestion(1, "Internal", "How are you", "Yes"));
		when(tokenService.authorizeTheRequest("OK")).thenReturn(true);
		when(questionsService.getAuditQuestionsByType("Internal")).thenReturn(questionsList);
		try {
			responseEntity = auditCheckListController.getQuestions("Internal", "OK");
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
		assertNotNull(responseEntity);

	}

	@Test
	public void testGetChecklistTokenInvalid() {
		ResponseEntity<?> responseEntity = null;
		when(tokenService.authorizeTheRequest("FORBIDDEN")).thenReturn(false);
		try {
			responseEntity = auditCheckListController.getQuestions("Internal", "FORBIDDEN");
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}

		assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
	}

	@Test
	public void testSaveResponses() {
		ResponseEntity<?> responseEntity = null;
		List<AuditQuestion> questionsList = new ArrayList<AuditQuestion>();
		questionsList.add(new AuditQuestion(1, "Internal", "How are you", "Yes"));
		when(tokenService.authorizeTheRequest("OK")).thenReturn(true);
		try {
			assertEquals(HttpStatus.OK, auditCheckListController.saveResponses(questionsList, "OK").getStatusCode());
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveResponseTokenInvalid() {
		ResponseEntity<?> responseEntity = null;
		List<AuditQuestion> questionsList = new ArrayList<AuditQuestion>();
		questionsList.add(new AuditQuestion(1, "Internal", "How are you", "Yes"));
		when(tokenService.authorizeTheRequest("FORBIDDEN")).thenReturn(false);
		try {
			responseEntity = auditCheckListController.saveResponses(questionsList, "FORBIDDEN");
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
		assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
	}
}