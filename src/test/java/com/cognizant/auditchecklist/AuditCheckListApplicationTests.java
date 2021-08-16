package com.cognizant.auditchecklist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.auditchecklist.controller.AuditCheckListController;
import com.cognizant.auditchecklist.exception.AuthorizationException;
import com.cognizant.auditchecklist.feign.AuthorisingClient;
import com.cognizant.auditchecklist.model.AuditQuestion;
import com.cognizant.auditchecklist.repository.AuditQuestionRepo;
import com.cognizant.auditchecklist.service.AuditCheckListService;

@SpringBootTest
class AuditCheckListApplicationTests {

	@Mock
	AuthorisingClient authoringClient;
	@Mock
	AuditCheckListService auditCheckListService;
	@Mock
	AuditQuestion auditQuestion;
	@InjectMocks
	AuditCheckListController auditCheckListController;
	@Mock
	AuditQuestionRepo auditQuestionRepo;

	@Test
	void contextLoads() {
		assertNotNull(auditCheckListController);
	}

	@Test
	public void testGetQuestionsType() {
		List<AuditQuestion> questionsList = new ArrayList<AuditQuestion>();
		questionsList.add(new AuditQuestion(1, "Internal", "Is the SIT and UAT sign-off available?", "Yes"));
		when(auditCheckListService.getAuditQuestionsByType("Internal")).thenReturn(questionsList);
	}

	@Test
	public void testSaveResponses() throws AuthorizationException {
		List<AuditQuestion> questionsList = new ArrayList<AuditQuestion>();
		questionsList.add(
				new AuditQuestion(1, "SOX", "Have all Change requests been approved by the application owner?", "Yes"));
		assertThat(questionsList).isNotNull();
	}
}
