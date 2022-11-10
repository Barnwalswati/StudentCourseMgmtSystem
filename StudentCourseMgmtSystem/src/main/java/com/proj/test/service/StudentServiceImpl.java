package com.proj.test.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.test.dao.StudentRepository;
import com.proj.test.entity.Student;
import com.proj.test.exception.ResourceNotFoundException;

@Service
public class StudentServiceImpl implements IStudentService {

	private StudentRepository stuRepo;
	
	@Autowired
	public StudentServiceImpl(StudentRepository stuRepo) {
		this.stuRepo = stuRepo;
	}

	@Transactional
	@Override
	public Student createStudent(Student student) {
		stuRepo.save(student);
		return student;
	}

	@Override
	public Student getStudentById(Long id) {
		Student student = stuRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("student", "id", id));
		return student;
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> students = stuRepo.findAll();
		return students;
	}

	@Transactional
	@Override
	public Student updateStudentById(Student student, Long id) {
		Student updatedstu = stuRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("student", "id", id));
		updatedstu.setName(student.getName());
		updatedstu.setEmail(student.getEmail());
		return stuRepo.save(updatedstu);
	}

	@Transactional
	@Override
	public void deleteStudentById(Long Id) {
		stuRepo.deleteById(Id);
	}

}
