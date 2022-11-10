package com.proj.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.test.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	//List<Course> findCourseById(Long studentId);
}
