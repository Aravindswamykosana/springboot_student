package com.jsp.springboot_student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailWrongException extends RuntimeException{
	private String msg="student email has been wrong";
}
