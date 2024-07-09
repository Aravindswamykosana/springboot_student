package com.jsp.springboot_student.dao;

import java.util.Optional;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.springboot_student.entity.Student;
import com.jsp.springboot_student.repo.StudentRepo;

@Repository
public class StudentCrud {
	@Autowired
	private StudentRepo repo;
	
	public Student saveStud(Student student) {
		 return repo.save(student);
	}
	
	public String loginStudent(String email,String pwd) {
		Student db = repo.findByEmail(email);
		if(db!=null) {
			if(db.getPwd().equals(pwd)) {
				return "login succesfull";
			}
			else {
				return "wrong pwd ra mama";
			}
		}
		return "wrong email ra mama";
	}
	
	public Student fetch(int id) {
		Optional<Student> db = repo.findById(id);
		if(db.isPresent()) {
			return repo.findById(id).get();
		}
		else
			return null;
	}
	
	public Student update(Student student) {
		Student db = repo.findByEmail(student.getEmail());
		if(db!=null) {
			if(student.getEmail()!=null) {
				db.setEmail(student.getEmail());
			}
			if(student.getAddress()!=null) {
				db.setAddress(student.getAddress());
			}
			if(student.getName()!=null) {
				db.setName(student.getName());
			}
			if(student.getPhone()!=0) {
				db.setPhone(student.getPhone());
			}
			if(student.getPwd()!=null) {
				db.setPwd(student.getPwd());
			}
			return repo.save(db);
		}
		else {
			return null;
		}
	}
	
	public Student delete(int id) {
		Optional<Student> db = repo.findById(id);
		if(db.isPresent()){
			repo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
}
