package com.latif.logservice.service.impl;

import com.latif.logservice.dto.BaseResponse;
import com.latif.logservice.dto.input.LogInput;
import com.latif.logservice.model.Log;
import com.latif.logservice.service.LogService;
import com.latif.logservice.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    public void setMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    private final LogRepository logRepository;

    @Override
    public Log create(LogInput input){
        Log log = this.mapper.map(input, Log.class);
        log.setDate();
        logRepository.save(log);
        return log;
    }

    @Override
    public ResponseEntity<BaseResponse> getAll(){
        Iterable<Log> logs = logRepository.findAll();
        List<Log> logList = IterableUtils.toList(logs);
        return new ResponseEntity<BaseResponse>(new BaseResponse<>(logList), HttpStatus.OK);
    }
}
