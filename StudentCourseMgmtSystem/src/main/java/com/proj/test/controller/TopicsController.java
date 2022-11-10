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

import com.proj.test.entity.Topic;
import com.proj.test.service.ITopicService;

@RestController
@RequestMapping("/student-course-mgmt")
public class TopicsController {

	private ITopicService topicService;

	@Autowired
	public TopicsController(ITopicService topicService) {
		this.topicService = topicService;
	}
	
	@GetMapping("/courses/{courseId}/topics")
	public List<Topic> getAllTopicsByCourseId(@PathVariable Long courseId) {
		return topicService.getAllTopicsByCourseId(courseId);
	}
	@GetMapping("/topics/{id}")
	public Topic getTopicById(@PathVariable Long id) {
		return topicService.getTopicById(id);
	}
	
	@PostMapping("/courses/{courseId}/topics")
	public Topic createTopic(@PathVariable Long courseId, @RequestBody Topic topic) {
		return topicService.createTopic(courseId, topic);
	}
	@PutMapping("/topics/{id}")
	public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
	    return new ResponseEntity<>(topicService.updateTopicById(id, topic), HttpStatus.OK);
	}
	@DeleteMapping("/topics/{id}")
	public ResponseEntity<String> deleteTopicById(@PathVariable Long id) {
	    topicService.deleteTopicById(id);
	    return new ResponseEntity<String>("Topic deleted successfully", HttpStatus.OK);
	}
	@DeleteMapping("/courses/{courseId}/topics")
	public ResponseEntity<String> deleteAllTopicsFromCourse(@PathVariable Long courseId) {
		topicService.deleteAllTopicFromCourse(courseId);
	    return new ResponseEntity<String>("All Topics deleted successfully from Given course", HttpStatus.OK);
	}
	
}
