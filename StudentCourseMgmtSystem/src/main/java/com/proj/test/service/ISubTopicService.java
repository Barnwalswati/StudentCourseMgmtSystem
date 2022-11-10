package com.proj.test.service;

import java.util.List;

import com.proj.test.entity.SubTopic;

public interface ISubTopicService {

	SubTopic createSubTopic(Long topicId, SubTopic subTopic);
	SubTopic getSubTopicById(Long id);
	List<SubTopic> getAllSubTopicsByTopicId(Long topicId);
	SubTopic updateSubTopicById(Long id, SubTopic subTopic);
	void deleteSubTopicById(Long id);
	void deleteAllSubTopicFromTopic(Long topicId);
}
