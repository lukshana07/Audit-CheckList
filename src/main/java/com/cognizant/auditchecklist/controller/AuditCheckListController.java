package com.cognizant.auditchecklist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.auditchecklist.exception.AuthorizationException;
import com.cognizant.auditchecklist.feign.AuthorisingClient;
import com.cognizant.auditchecklist.model.AuditQuestion;
import com.cognizant.auditchecklist.service.AuditCheckListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/checklist")
@Api(value = "AuditCheckListController")
public class AuditCheckListController {

	@Autowired
	private AuditCheckListService auditCheckListService;
	@Autowired
	private AuthorisingClient authorisingClient;

	@ApiOperation(value = "Get List of Questions", response = ResponseEntity.class, tags = "getQuestions")
	@PostMapping(value = "/GetQuestions")
	public ResponseEntity<?> getQuestions(@RequestBody String auditType,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws IndexOutOfBoundsException, AuthorizationException {

		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			List<AuditQuestion> questions = new ArrayList<>();
			ResponseEntity<?> responseEntity;
			try {
				questions = auditCheckListService.getAuditQuestionsByType(auditType);
			} catch (Exception e) {
				responseEntity = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
				return responseEntity;
			}
			responseEntity = new ResponseEntity<List<AuditQuestion>>(questions, HttpStatus.OK);
			return responseEntity;
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);

		}
	}

	@ApiOperation(value = "Save Question", response = ResponseEntity.class, tags = "saveResponses")
	@PostMapping(value = "/SaveQuestions")
	public ResponseEntity<?> saveResponses(@RequestBody List<AuditQuestion> auditQuestionResponse,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			List<AuditQuestion> questions = new ArrayList<>();
			ResponseEntity<?> responseEntity;
			questions = auditCheckListService.saveReponses(auditQuestionResponse);
			responseEntity = new ResponseEntity<List<AuditQuestion>>(questions, HttpStatus.OK);
			return responseEntity;
		} else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}

}
