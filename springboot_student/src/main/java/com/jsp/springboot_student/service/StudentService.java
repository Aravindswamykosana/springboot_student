package com.jsp.springboot_student.service;

import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsp.springboot_student.dao.StudentCrud;
import com.jsp.springboot_student.dto.StudentEmail;
import com.jsp.springboot_student.entity.Student;
import com.jsp.springboot_student.exception.DeleteException;
import com.jsp.springboot_student.exception.EmailWrongException;
import com.jsp.springboot_student.exception.PwdException;
import com.jsp.springboot_student.exception.StudentIdNotFoundException;
import com.jsp.springboot_student.exception.UpdateException;
import com.jsp.springboot_student.repo.StudentRepo;
import com.jsp.springboot_student.util.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class StudentService {
	@Autowired
	private JavaMailSender emailsender;
	@Autowired
	private StudentCrud crud;
	@Autowired
	private StudentRepo repo;
	public void sendMsg(String email) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("aarvindkosana@gmail.com");
		message.setTo(email);
		message.setSubject("otp success");
		message.setText("dont share otp to anyone");
		emailsender.send(message);
	}
	public void sendOTP(String email) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("aarvindkosana@gmail.com");
		message.setTo(email);
		message.setSubject("you have recieved an otp");
		Random random=new Random();
		int otp=random.nextInt(100000);
		message.setText("your otp for login as:"+otp+"\nplease dont share otp for anyone");
		emailsender.send(message);
	}
	
	public void sendAttachment(String email) throws MessagingException{
		MimeMessage message=emailsender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom("aarvindkosana@gmail.com");
		helper.setTo(email);
		helper.setSubject("data saved");
		helper.setText("your data will be saved succesfully in our database");
		
		FileSystemResource file=new FileSystemResource(new File("C:\\Users\\ARAVIND\\Documents\\mypic.jpg"));
		helper.addAttachment(file.getFilename(), file);
		emailsender.send(message);
	}
	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student){
		Student db = crud.saveStud(student);
		ResponseStructure<Student> rs=new ResponseStructure<Student>();
		rs.setData(db);
		rs.setMessage("student saved succesfully.....!");
		rs.setStatus(HttpStatus.CREATED.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<Student>>(rs,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Student>> loginStudent(String email,String pwd) {
		Student db = repo.findByEmail(email);
		ResponseStructure<Student> rs=new ResponseStructure<Student>();
		if(crud.loginStudent(email, pwd).equals("login succesfull")) {
			rs.setData(db);
			rs.setMessage("student login succesfully.....!");
			rs.setStatus(HttpStatus.OK.value());
			rs.setDt(LocalDateTime.now());
			return new ResponseEntity<ResponseStructure<Student>>(rs,HttpStatus.OK);
		}
		else if(crud.loginStudent(email, pwd).equals("wrong pwd ra mama")) {
			throw new PwdException("student pwd has wrong");
		}
		else {
			throw new EmailWrongException("student email has wrong");
			}
	}
	
	public ResponseEntity<ResponseStructure<Student>> fetch(int id){
		Student db = crud.fetch(id);
		if(db!=null) {
			ResponseStructure<Student> rs=new ResponseStructure<Student>();
			rs.setData(db);
			rs.setMessage("student fetched succesfully.....!");
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setDt(LocalDateTime.now());
			return new ResponseEntity<ResponseStructure<Student>>(rs,HttpStatus.CREATED);
		}
		else {
			 throw new StudentIdNotFoundException("student id is:"+id+" not found in db & pls provide valid id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> update(Student student){
		Student db = crud.update(student);
		if(db!=null) {
			ResponseStructure<Student> rs=new ResponseStructure<Student>();
			rs.setData(db);
			rs.setMessage("student updated succesfully.....!");
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setDt(LocalDateTime.now());
			return new ResponseEntity<ResponseStructure<Student>>(rs,HttpStatus.CREATED);
		}
		else {
			throw new UpdateException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> delete(int id){
		Student db = crud.delete(id);
		if(db!=null) {
			ResponseStructure<Student> rs=new ResponseStructure<Student>();
			rs.setData(db);
			rs.setMessage("student deleted succesfully.....!");
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setDt(LocalDateTime.now());
			return new ResponseEntity<ResponseStructure<Student>>(rs,HttpStatus.CREATED);
		}
		else {
			throw new DeleteException();
		}
	}
}
