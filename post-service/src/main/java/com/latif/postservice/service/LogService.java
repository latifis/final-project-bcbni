package com.latif.postservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("finalprojectfix")
    private String topic;

    public void send(String message) {
        kafkaTemplate.send(topic, message);
    }
}
