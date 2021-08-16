package com.cognizant.auditchecklist.service;

import java.util.List;

import com.cognizant.auditchecklist.model.AuditQuestion;

public interface AuditCheckListService {

	public List<AuditQuestion> getAuditQuestionsByType(String auditType) throws IndexOutOfBoundsException;

	public List<AuditQuestion> saveReponses(List<AuditQuestion> auditQuestionsResponse);
}
