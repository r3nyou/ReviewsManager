package com.example.scheduler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonToPojoService {
    ObjectMapper mapper = new ObjectMapper();
    public <T> T  convert(String jsonStr,Class<T> type) throws JsonProcessingException {
        return mapper.readValue(jsonStr,type);
    }
}
