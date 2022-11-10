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

import com.proj.test.entity.SubTopic;
import com.proj.test.service.ISubTopicService;

@RestController
@RequestMapping("/student-course-mgmt")
public class SubTopicController {

	private ISubTopicService subTopicService;
	@Autowired
	public SubTopicController(ISubTopicService subTopicService) {
		this.subTopicService = subTopicService;
	}
	
	@GetMapping("/topics/{topicId}/subTopics")
	public List<SubTopic> getAllTopicsByCourseId(@PathVariable Long topicId) {
		return subTopicService.getAllSubTopicsByTopicId(topicId);
	}
	@GetMapping("/subTopics/{id}")
	public SubTopic getSubTopicById(@PathVariable Long id) {
		return subTopicService.getSubTopicById(id);
	}
	
	@PostMapping("/topics/{topicId}/subTopics")
	public SubTopic createTopic(@PathVariable Long topicId, @RequestBody SubTopic subTopic) {
		return subTopicService.createSubTopic(topicId, subTopic);
	}
	@PutMapping("/subTopics/{id}")
	public ResponseEntity<SubTopic> updateSubTopicById(@PathVariable Long id, @RequestBody SubTopic subTopic) {
	    return new ResponseEntity<>(subTopicService.updateSubTopicById(id, subTopic), HttpStatus.OK);
	}
	@DeleteMapping("/subTopics/{id}")
	public ResponseEntity<String> deleteSubTopicById(@PathVariable Long id) {
		subTopicService.deleteSubTopicById(id);
	    return new ResponseEntity<String>("SubTopic deleted successfully", HttpStatus.NO_CONTENT);
	}
	@DeleteMapping("/topics/{topicId}/subTopics")
	public ResponseEntity<String> deleteAllSubTopicsFromTopic(@PathVariable Long topicId) {
		subTopicService.deleteAllSubTopicFromTopic(topicId);
	    return new ResponseEntity<String>("All SubTopics deleted successfully from Given topic", HttpStatus.NO_CONTENT);
	}
	
}
