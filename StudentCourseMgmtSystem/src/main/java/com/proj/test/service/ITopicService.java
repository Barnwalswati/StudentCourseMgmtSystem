package com.proj.test.service;

import java.util.List;

import com.proj.test.entity.Topic;

public interface ITopicService {

	Topic createTopic(Long courseId, Topic topic);
	Topic getTopicById(Long id);
	List<Topic> getAllTopicsByCourseId(Long courseId);
	Topic updateTopicById(Long id, Topic topic);
	void deleteTopicById(Long id);
	void deleteAllTopicFromCourse(Long courseId);
}
