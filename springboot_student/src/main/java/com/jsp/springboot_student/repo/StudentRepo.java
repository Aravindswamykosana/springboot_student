package com.jsp.springboot_student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.springboot_student.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	@Query("select a from Student a where a.email=?1")
	public abstract Student findByEmail(String email);
}
