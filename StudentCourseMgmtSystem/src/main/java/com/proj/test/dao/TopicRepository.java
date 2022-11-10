package com.proj.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.test.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
