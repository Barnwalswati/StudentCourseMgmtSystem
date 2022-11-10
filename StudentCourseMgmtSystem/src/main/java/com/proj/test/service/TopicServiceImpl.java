package com.proj.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.test.dao.CourseRepository;
import com.proj.test.dao.TopicRepository;
import com.proj.test.entity.Course;
import com.proj.test.entity.Topic;
import com.proj.test.exception.ResourceNotFoundException;

@Service
public class TopicServiceImpl implements ITopicService {

	private TopicRepository topicRepo;
	private CourseRepository courseRepo;
	@Autowired
	public TopicServiceImpl(TopicRepository topicRepo, CourseRepository courseRepo) {
		this.topicRepo = topicRepo;
		this.courseRepo = courseRepo;
	}

	@Transactional
	@Override
	public Topic createTopic(Long courseId, Topic topic) {
		Topic newtopic = courseRepo.findById(courseId).map(course -> {
			course.getTopics().add(topic);
			return topicRepo.save(topic);}).orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
		return newtopic;
	}

	@Override
	public Topic getTopicById(Long id) {
		Topic topic = topicRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("topic", "id", id));
		return topic;
	}

	@Override
	public List<Topic> getAllTopicsByCourseId(Long courseId) {
		Course course = courseRepo.findById(courseId)
		        .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
		List<Topic> topics = new ArrayList<Topic>();
		topics.addAll(course.getTopics());
		return topics;
	}

	@Transactional
	@Override
	public Topic updateTopicById(Long id, Topic topic) {
		Topic updatedTopic = topicRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("topic", "id", id));
		updatedTopic.setName(topic.getName());
		return topicRepo.save(updatedTopic);
	}

	@Transactional
	@Override
	public void deleteTopicById(Long id) {
		topicRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAllTopicFromCourse(Long courseId) {
		Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("course", "id", courseId));
		course.getTopics().clear();
		courseRepo.save(course);
	}

}
