package com.cognizant.auditchecklist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.auditchecklist.model.AuditQuestion;

public interface AuditQuestionRepo extends JpaRepository<AuditQuestion, Integer> {
	List<AuditQuestion> findAuditQuestionByAuditType(String auditType);
}
