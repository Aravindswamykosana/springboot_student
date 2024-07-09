package com.jsp.springboot_student.dto;

import lombok.Data;

@Data
public class StudentEmail {
	private String to;
	private String subject;
	private String body;
	private String attach; 
}
