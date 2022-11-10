package com.proj.test.service;

import java.util.List;

import com.proj.test.entity.Student;

public interface IStudentService {

	Student createStudent(Student student);
	Student getStudentById(Long id);
	List<Student> getAllStudent();
	Student updateStudentById(Student student, Long id);
	void deleteStudentById(Long Id);
}
