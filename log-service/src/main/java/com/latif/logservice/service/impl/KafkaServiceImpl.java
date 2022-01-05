package com.latif.logservice.service.impl;

import com.google.gson.Gson;
import com.latif.logservice.dto.input.LogInput;
import com.latif.logservice.service.KafkaService;
import com.latif.logservice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {
    private final static String topic = "finalprojectfix";
    private final static String groupId = "bni46Group";
    public static List<String> messages = new ArrayList<>();
    private final LogService logService;

    @KafkaListener(topics = topic, groupId = groupId)
    @Override
    public void listen(String message) {
        Gson g = new Gson();
        LogInput logInput = g.fromJson(message, LogInput.class);
        logService.create(logInput);
    }
}
