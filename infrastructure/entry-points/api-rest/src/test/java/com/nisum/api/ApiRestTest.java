package com.nisum.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.api.config.util.JsonSchemaValidator;
import com.nisum.model.request.Request;
import com.nisum.model.response.Response;
import com.nisum.usecase.request.RegisterUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ApiRestTest {

    private ApiRest apiRest;
    private JsonSchemaValidator jsonSchemaValidator;
    private ObjectMapper objectMapper;
    private RegisterUseCase registerUseCase;

    @BeforeEach
    void setup() {
        jsonSchemaValidator = mock(JsonSchemaValidator.class);
        objectMapper = mock(ObjectMapper.class);
        registerUseCase = mock(RegisterUseCase.class);
        apiRest = new ApiRest(jsonSchemaValidator, objectMapper, registerUseCase);
    }

    @Test
    void emailExistExpected409() {
        JsonNode mockBody = mock(JsonNode.class);
        Request mockRequest = new Request();
        mockRequest.setUsername("test@example.com");

        doNothing().when(jsonSchemaValidator).validateWithJsonSchema(any(JsonNode.class));
        when(objectMapper.convertValue(any(JsonNode.class), eq(Request.class))).thenReturn(mockRequest);
        when(registerUseCase.existsByEmail("test@example.com")).thenReturn(true);

        ResponseEntity<?> response = apiRest.register(mockBody);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(Map.of("mensaje", "El correo ya esta registrado"), response.getBody());
    }

    @Test
    void emailDoesNotExist() {
        JsonNode mockBody = mock(JsonNode.class);
        Request mockRequest = new Request();
        mockRequest.setUsername("test@example.com");

        Response mockResponse = Response.builder()
                .id(UUID.randomUUID())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("example-token")
                .isActive(true)
                .build();

        doNothing().when(jsonSchemaValidator).validateWithJsonSchema(any(JsonNode.class));
        when(objectMapper.convertValue(any(JsonNode.class), eq(Request.class))).thenReturn(mockRequest);
        when(registerUseCase.existsByEmail("test@example.com")).thenReturn(false);
        when(registerUseCase.register(mockRequest)).thenReturn(mockResponse);

        ResponseEntity<?> response = apiRest.register(mockBody);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }




}
