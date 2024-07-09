package com.jsp.springboot_student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jsp.springboot_student.entity.Student;
import com.jsp.springboot_student.service.StudentService;
import com.jsp.springboot_student.util.ResponseStructure;

@RestController
public class StudentMain {
	@Autowired
	private StudentService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Student>> save(@RequestBody Student student) {
		return service.saveStudent(student);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Student>>  loginStudent(@RequestParam String email,@RequestParam String pwd) {
		return service.loginStudent(email, pwd);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<Student>> fetch(@RequestParam int id){
		return service.fetch(id);
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseStructure<Student>> update(@RequestBody Student student){
		return service.update(student);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<ResponseStructure<Student>> delete(@RequestParam int id){
		return service.delete(id);
	}
}
