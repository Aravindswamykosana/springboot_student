package com.jsp.springboot_student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteException extends RuntimeException{
	private String msg="please provide valid student id mama";
}
