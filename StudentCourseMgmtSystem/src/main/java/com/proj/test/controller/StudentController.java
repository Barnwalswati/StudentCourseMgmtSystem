package com.proj.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.test.entity.Student;
import com.proj.test.service.IStudentService;

@RestController
@RequestMapping("/student-course-mgmt/students")
public class StudentController {

	private IStudentService stuService;

	@Autowired
	public StudentController(IStudentService stuService) {
		this.stuService = stuService;
	}
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		return new ResponseEntity<Student>(stuService.createStudent(student), HttpStatus.CREATED);
	}
	@GetMapping("/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
		return new ResponseEntity<Student>(stuService.getStudentById(studentId), HttpStatus.OK);
	}
	@GetMapping
	public List<Student> getAllStudent(){
		List<Student> s1 = stuService.getAllStudent();
		return s1;
	}
	@PutMapping("/{studentId}")
	public ResponseEntity<Student> updateStudentById(@RequestBody Student student, @PathVariable Long studentId) {
		return new ResponseEntity<Student>(stuService.updateStudentById(student, studentId), HttpStatus.OK);
	}
	@DeleteMapping("/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){
		stuService.deleteStudentById(studentId);
		return new ResponseEntity<String>("Student deleted successfully", HttpStatus.OK);
	} 
}
