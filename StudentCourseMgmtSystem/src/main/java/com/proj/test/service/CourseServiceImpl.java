package com.proj.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.test.dao.CourseRepository;
import com.proj.test.dao.StudentRepository;
import com.proj.test.entity.Course;
import com.proj.test.entity.Student;
import com.proj.test.exception.ResourceNotFoundException;

@Service
public class CourseServiceImpl implements ICourseService {
	
	private CourseRepository courseRepo;
	private StudentRepository studentRepo;
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepo, StudentRepository studentRepo) {
		this.courseRepo = courseRepo;
		this.studentRepo = studentRepo;
	}

	@Transactional
	@Override
	public Course addCourse(Course course) {
		return courseRepo.save(course);
	}
	
	@Transactional
	@Override
	public void addCourseToStudent(Long studentId, Long courseId) {
		Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("course", "id", courseId));
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("course", "id", courseId));
		student.addCourse(course);
		
//		Course newCourse = studentRepo.findById(studentId).map(student -> {
//			student.addCourse(course);
//			return /*courseRepo.save(course)*/course;}).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
//		return newCourse;
	}

	@Override
	public Course getCourseById(Long id) {
		Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("course", "id", id));
		return course;
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}

	@Override
	public List<Course> getAllCoursesByStudentId(Long studentId) {
		Student student = studentRepo.findById(studentId)
		        .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
		List<Course> courses = new ArrayList<Course>();
		courses.addAll(student.getCourses());
		return courses;
	}
	
	@Override
	public List<Student> getAllStudentsByCourseId(Long courseId) {
		Course course = courseRepo.findById(courseId)
		        .orElseThrow(() -> new ResourceNotFoundException("course", "id", courseId));
		List<Student> students = new ArrayList<Student>();
		students.addAll(course.getStudents());
		return students;
	}
	
	@Transactional
	@Override
	public Course updateCourseById(Course course, Long id) {
		Course updatedCourse = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("course", "id", id));
		updatedCourse.setName(course.getName());
		return courseRepo.save(updatedCourse);
	}

	@Transactional
	@Override
	public void deleteCourseById(Long id) {
		courseRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteCourseFromStudent(Long studentId, Long courseId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
		student.removeCourse(courseId);
		studentRepo.save(student);
	}

	

}
