
package com.cognizant.auditchecklist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.auditchecklist.controller.AuditCheckListController;
import com.cognizant.auditchecklist.feign.AuthorisingClient;
import com.cognizant.auditchecklist.model.AuditQuestion;
import com.cognizant.auditchecklist.repository.AuditQuestionRepo;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration
class AuditCheckListServiceTest {

	/**
	 * 
	 * This class contains test cases for the QuestionsService class which are
	 * written using junit and mockito
	 *
	 */
	@Mock
	AuditQuestionRepo questionsRespository;

	@InjectMocks
	AuditCheckListServiceImplementation questionsService;

	@Mock
	AuthorisingClient tokenService;

	@InjectMocks
	AuditCheckListController auditCheckListController;

	/**
	 * test to check whether it returns a list
	 * 
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	public void testGetQuestionsList() throws IndexOutOfBoundsException {
		List<AuditQuestion> questions = new ArrayList<>();
		questions.add(
				new AuditQuestion(1, "Internal", "Have all Change requests followed SDLC before PROD move?", "Yes"));
		when(questionsRespository.findAuditQuestionByAuditType("Internal")).thenReturn(questions);
		assertEquals(questions, questionsService.getAuditQuestionsByType("Internal"));
	}

	/**
	 * test questions when list throws IndexOutOfBoundsException
	 */

	@Test
	public void testQuestionsListthrowsIndexOutOfBoundsException() {
		when(questionsRespository.findAuditQuestionByAuditType("abc")).thenThrow(IndexOutOfBoundsException.class);
		assertThrows(IndexOutOfBoundsException.class, () -> questionsService.getAuditQuestionsByType("abc"));
	}

	/**
	 * 
	 */
	/*
	 * @Test public void testgetQuestions() { ResponseEntity<?> responseEntity =
	 * null; List<AuditQuestion> questionsList = new ArrayList<>();
	 * questionsList.add(new AuditQuestion(1, "Internal", "How are you", "Yes"));
	 * when(tokenService.authorizeTheRequest("OK")).thenReturn(true);
	 * when(questionsService.getAuditQuestionsByType("Internal")).thenReturn(
	 * questionsRespository.findAuditQuestionByAuditType("Internal")); try {
	 * responseEntity = auditCheckListController.getQuestions("Internal", "OK"); }
	 * catch (IndexOutOfBoundsException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (AuthorizationException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * assertNotNull(responseEntity);
	 * 
	 * }
	 */
	@Test
	public void testSaveResponses() {
		List<AuditQuestion> questions = new ArrayList<>();
		questions.add(
				new AuditQuestion(1, "Internal", "Have all Change requests followed SDLC before PROD move?", "Yes"));
		when(questionsRespository.saveAll(questions)).thenReturn(questions);
		assertEquals(questions, questionsService.saveReponses(questions));
	}
}
