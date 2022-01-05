package com.latif.logservice.service;

import com.latif.logservice.dto.BaseResponse;
import com.latif.logservice.dto.input.LogInput;
import com.latif.logservice.model.Log;
import org.springframework.http.ResponseEntity;

public interface LogService {
    Log create(LogInput input);
    ResponseEntity<BaseResponse> getAll();
}
