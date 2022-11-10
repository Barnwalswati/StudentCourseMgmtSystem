package com.proj.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.test.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	//List<Student> findStudentsById(Long courseId);
}
