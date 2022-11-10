package com.proj.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.test.dao.SubTopicRepository;
import com.proj.test.dao.TopicRepository;
import com.proj.test.entity.SubTopic;
import com.proj.test.entity.Topic;
import com.proj.test.exception.ResourceNotFoundException;

@Service
public class SubTopicServiceImpl implements ISubTopicService {

	private SubTopicRepository subTopicRepo;
	private TopicRepository topicRepo;
	@Autowired
	public SubTopicServiceImpl(SubTopicRepository subTopicRepo, TopicRepository topicRepo) {
		this.subTopicRepo = subTopicRepo;
		this.topicRepo = topicRepo;
	}

	@Transactional
	@Override
	public SubTopic createSubTopic(Long topicId, SubTopic subTopic) {
		SubTopic newSubTopic = topicRepo.findById(topicId).map(topic -> {
			topic.getSubTopics().add(subTopic);
			return subTopicRepo.save(subTopic);}).orElseThrow(() -> new ResourceNotFoundException("topic", "id", topicId));
		return newSubTopic;
	}

	@Override
	public SubTopic getSubTopicById(Long id) {
		SubTopic subTopic = subTopicRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("subtopic", "id", id));
		return subTopic;
	}

	@Override
	public List<SubTopic> getAllSubTopicsByTopicId(Long topicId) {
		Topic topic = topicRepo.findById(topicId)
		        .orElseThrow(() -> new ResourceNotFoundException("topic", "id", topicId));
		List<SubTopic> subTopics = new ArrayList<SubTopic>();
		subTopics.addAll(topic.getSubTopics());
		return subTopics;
	}

	@Transactional
	@Override
	public SubTopic updateSubTopicById(Long id, SubTopic subTopic) {
		SubTopic updatedSubTopic = subTopicRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("SubTopic", "id", id));
		updatedSubTopic.setName(subTopic.getName());
		return subTopicRepo.save(updatedSubTopic);
	}

	@Transactional
	@Override
	public void deleteSubTopicById(Long id) {
		subTopicRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAllSubTopicFromTopic(Long topicId) {
		Topic topic = topicRepo.findById(topicId).orElseThrow(() -> new ResourceNotFoundException("topic", "id", topicId));
		topic.getSubTopics().clear();
		topicRepo.save(topic);
	}

}
