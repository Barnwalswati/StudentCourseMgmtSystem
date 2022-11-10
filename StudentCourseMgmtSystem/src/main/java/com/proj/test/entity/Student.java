package com.proj.test.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Owning side
@Entity
@Table(name = "student")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST})
	@JoinTable(
    		name="student_course",
    		joinColumns = {@JoinColumn(name="student_id", referencedColumnName = "id")},
    		inverseJoinColumns={@JoinColumn(name="course_id", referencedColumnName = "id")}
			)
	private Set<Course> courses = new HashSet<>();

	public void addCourse(Course course) {
	    this.courses.add(course);
	    course.getStudents().add(this);
	  }
	  
	public void removeCourse(long courseId) {
		Course course = this.courses.stream().filter(c -> c.getId() == courseId).findFirst().orElse(null);
	    if (course != null) {
	      this.courses.remove(course);
	      course.getStudents().remove(this);
	    }
	  }
	
}
