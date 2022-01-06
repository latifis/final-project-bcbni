package com.latif.logservice.controller;

import com.latif.logservice.dto.BaseResponse;
import com.latif.logservice.dto.input.LogInput;
import com.latif.logservice.model.Log;
import com.latif.logservice.service.KafkaService;
import com.latif.logservice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {
    @Autowired
    private KafkaService kafkaService;

    private final LogService logService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> create(@Valid @RequestBody LogInput input){
        Log logCreated = logService.create(input);
        return ResponseEntity.ok(new BaseResponse<>(input));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        return logService.getAll();
    }
}
