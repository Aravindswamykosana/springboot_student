package com.jsp.springboot_student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentIdNotFoundException extends RuntimeException{
	private String msg="student details not found this id";
}
