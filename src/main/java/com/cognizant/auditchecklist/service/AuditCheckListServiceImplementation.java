package com.cognizant.auditchecklist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.auditchecklist.model.AuditQuestion;
import com.cognizant.auditchecklist.repository.AuditQuestionRepo;

@Service
public class AuditCheckListServiceImplementation implements AuditCheckListService {

	@Autowired
	AuditQuestionRepo repo;
	
	@Override
	public List<AuditQuestion> getAuditQuestionsByType(String auditType) throws IndexOutOfBoundsException {
		if(repo.findAuditQuestionByAuditType(auditType).isEmpty()) {
			throw new IndexOutOfBoundsException();
		} else {
			return repo.findAuditQuestionByAuditType(auditType);
		}
	}

	@Override
	public List<AuditQuestion> saveReponses(List<AuditQuestion> auditQuestionsResponse) {
		return repo.saveAll(auditQuestionsResponse);
	}

	

	
	
	
}
