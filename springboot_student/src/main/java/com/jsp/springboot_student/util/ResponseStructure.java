package com.jsp.springboot_student.util;
import java.time.LocalDateTime;
import lombok.Data;
@Data
public class ResponseStructure<T>{
	private int status;
	private T data;
	private String message;
	private LocalDateTime dt;
//	private LocalDateTime time=LocalDateTime.now(); //both are same
}
