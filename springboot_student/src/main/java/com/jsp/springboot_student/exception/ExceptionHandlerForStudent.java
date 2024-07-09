package com.jsp.springboot_student.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.springboot_student.entity.Student;
import com.jsp.springboot_student.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForStudent {
	@ExceptionHandler(StudentIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundHandle(StudentIdNotFoundException ex){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(ex.getMsg());
		rs.setMessage("student data not found successfully");
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>>SQLIntegrityConstraintViolationException (SQLIntegrityConstraintViolationException ex){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData("please provide valid email");
		rs.setMessage("student email is already exists");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PwdException.class)
	public ResponseEntity<ResponseStructure<String>> PwdWrong(PwdException p){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(p.getMsg());
		rs.setMessage("student login failed pwd wrong.....!");
		rs.setStatus(HttpStatus.PRECONDITION_FAILED.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(EmailWrongException.class)
	public ResponseEntity<ResponseStructure<String>> EmailWrong(EmailWrongException p){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		//rs.setData(p.getMsg());
		rs.setMessage("student login failed email wrong.....!");
		rs.setStatus(HttpStatus.PRECONDITION_FAILED.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(UpdateException.class)
	public ResponseEntity<ResponseStructure<String>> UpdateException(UpdateException p){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(p.getMsg());
		rs.setMessage("student can not be updated.....!");
		rs.setStatus(HttpStatus.PRECONDITION_FAILED.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(DeleteException.class)
	public ResponseEntity<ResponseStructure<String>> DeleteException(DeleteException p){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(p.getMsg());
		rs.setMessage("student can not be deleted.....!");
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
}
