package com.student.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.kafka.producer.Producer;

@RestController
@RequestMapping(value = "/student/api")
public class KafkaController {
	@Autowired
	public Producer producer;

	@GetMapping(value = "/publish/{message}")
	public void sendMessageToKafkaTopic(@PathVariable("message") String message) {
		this.producer.sendMessage(message);
	}
}