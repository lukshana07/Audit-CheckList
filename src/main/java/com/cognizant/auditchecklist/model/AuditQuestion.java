package com.cognizant.auditchecklist.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AuditQuestions")
public class AuditQuestion {
	@Id
	@ApiModelProperty(notes = "Question ID", name = "questionId")
	private int questionId;
	@ApiModelProperty(notes = "Audit Type", name = "auditType")
	private String auditType;
	@ApiModelProperty(notes = "Question Model", name = "question")
	private String question;
	@ApiModelProperty(notes = "Response", name = "response")
	private String response;
}
