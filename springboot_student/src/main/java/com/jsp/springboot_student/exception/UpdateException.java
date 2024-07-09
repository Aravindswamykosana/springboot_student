package com.jsp.springboot_student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateException extends RuntimeException{
	private String msg="please provide valid email for updating";
}
