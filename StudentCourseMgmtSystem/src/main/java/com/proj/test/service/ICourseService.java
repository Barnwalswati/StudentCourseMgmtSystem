package com.proj.test.service;

import java.util.List;

import com.proj.test.entity.Course;
import com.proj.test.entity.Student;

public interface ICourseService {
	
	Course addCourse(Course course);
	void addCourseToStudent(Long studentId, Long courseId);
	Course getCourseById(Long id);
	List<Course> getAllCourses();
	List<Course> getAllCoursesByStudentId(Long studentId);
	List<Student> getAllStudentsByCourseId(Long courseId);
	Course updateCourseById(Course course, Long id);
	void deleteCourseById(Long id);
	void deleteCourseFromStudent(Long studentId, Long courseId);
}
