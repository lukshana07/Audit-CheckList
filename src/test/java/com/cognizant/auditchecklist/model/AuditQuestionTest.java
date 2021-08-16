package com.cognizant.auditchecklist.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 
 *		   This class contains test cases for the AuditQuestion model class
 *         which are written using junit and mockito
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class AuditQuestionTest{

		@Mock
		AuditQuestion questions;
		
		@BeforeEach
		void setUp() throws Exception {
			questions = new AuditQuestion(1,"Internal","Is data deleted with permission of user?","Yes");
		}
		
		/**
		 * Checking if AuditQuestion class is loading or not.
		 */
		@Test
		@DisplayName("Checking if AuditQuestion class is loading or not.")
		void claimIsLoadedOrNot() {
			assertThat(questions).isNotNull();
		}

		/**
		 * 
		 * Testing AuditQuestion Constructor
		 */
		@Test
		public void testBenefitsConstructor() {
			assertEquals(1, questions.getQuestionId());
			assertEquals("Internal", questions.getAuditType());
			assertEquals("Is data deleted with permission of user?", questions.getQuestion());
			assertTrue("Yes".equalsIgnoreCase(questions.getResponse()));
		}

		/**
		 * 
		 * Testing Getters and setters
		 */
		@Test
		public void testGettersSetters() {
			questions.setQuestionId(1);
			questions.setAuditType("Internal");
			questions.setQuestion("Is data deleted with permission of user?");
			questions.setResponse("Yes");
			assertEquals(questions.getQuestionId(), 1);
			assertEquals(questions.getAuditType(), "Internal");
			assertEquals(questions.getQuestion(), "Is data deleted with permission of user?");
			assertTrue("Yes".equalsIgnoreCase(questions.getResponse()));
		}

		@Test
		public void testHashCodes() {
			AuditQuestion tempAuditquestion = new AuditQuestion(1,"Internal","Is data deleted with permission of user?","Yes");
			assertEquals(questions.hashCode(),tempAuditquestion.hashCode());
		}
		@Test
		public void testEquals() {
			AuditQuestion tempAuditquestion = new AuditQuestion(1,"Internal","Is data deleted with permission of user?","Yes");
			assertEquals(questions, tempAuditquestion);
		}
		
		/*
		 * 
		 * test the AuditQuestion no argsConstructor
		 */

		@Test
		public void testNoArgConstructor() {
			AuditQuestion ulc = new AuditQuestion();
			AuditQuestion ulc1 = new AuditQuestion();
			assertEquals(ulc, ulc1);
		}

		/*
		 * 
		 * test the AuditQuestion toString()
		 */
		@Test
	    public void testToString()
	    {
			String result = "AuditQuestion(questionId=" + questions.getQuestionId() + ", auditType=" + questions.getAuditType() + ", question=" + questions.getQuestion() + ", response=" + questions.getResponse() + ")";
			assertEquals(questions.toString(), result);
	    }

		
		
	}