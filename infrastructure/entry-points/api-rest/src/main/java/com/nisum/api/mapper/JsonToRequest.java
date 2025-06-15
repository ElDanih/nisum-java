package com.nisum.api.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.model.request.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonToRequest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Request toRequest(String json) {
        String test = "";
        return null;
    }
}
