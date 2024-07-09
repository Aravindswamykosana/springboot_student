package com.jsp.springboot_student.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PwdException extends RuntimeException{
	private String msg="password is wrong";
}
