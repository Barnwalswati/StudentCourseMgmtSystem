package com.proj.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.test.entity.Course;
import com.proj.test.entity.Student;
import com.proj.test.service.ICourseService;

@RestController
@RequestMapping("/student-course-mgmt")
public class CourseController {

	private ICourseService courseService;

	@Autowired
	public CourseController(ICourseService courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}
	@GetMapping("/courses/{id}")
	public Course getCourseById(@PathVariable Long id) {
		return courseService.getCourseById(id);
	}
	@GetMapping("/students/{studentId}/courses")
	public List<Course> getAllCoursesByStudentId(@PathVariable Long studentId) {
		return courseService.getAllCoursesByStudentId(studentId);
	}
	@GetMapping("/courses/{courseId}/students")
	public List<Student> getAllStudentsByCourseId(@PathVariable Long courseId) {
		return courseService.getAllStudentsByCourseId(courseId);
	}
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course) {
		return courseService.addCourse(course);
	}
	@PostMapping("/students/{studentId}/courses/{courseId}")
	public ResponseEntity<String> addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
		courseService.addCourseToStudent(studentId, courseId);
		return new ResponseEntity<String>("Student registered with the course successfully", HttpStatus.OK);
	}
	@DeleteMapping("/courses/{id}")
	public ResponseEntity<String> deleteCourseById(@PathVariable Long id) {
		Course course = courseService.getCourseById(id);
		List<Student> students = new ArrayList<Student>();
		students.addAll(course.getStudents());
		students.stream().forEach(student->courseService.deleteCourseFromStudent(student.getId(), id));
		courseService.deleteCourseById(id);
		return new ResponseEntity<String>("Course deleted successfully", HttpStatus.OK);
	}
	@DeleteMapping("/students/{studentId}/courses/{courseId}")
	public ResponseEntity<String> deletedCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId){
		courseService.deleteCourseFromStudent(studentId, courseId);
		return new ResponseEntity<String>("Course deleted successfully from given Student Id", HttpStatus.OK);
	}
}
