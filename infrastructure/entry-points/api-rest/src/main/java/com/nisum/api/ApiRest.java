package com.nisum.api;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.api.config.util.JsonSchemaValidator;
import com.nisum.model.request.Request;
import com.nisum.usecase.request.RegisterUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private final JsonSchemaValidator jsonSchemaValidator;
    private final ObjectMapper objectMapper;
    private static final String MESSAGE = "mensaje";
    private static final String EMAIL_ALREADY_EXISTS = "El correo ya esta registrado";

    private final RegisterUseCase registerUseCase;

    @GetMapping(path = "/path")
    public String commandName() {

        return "Hello world!";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/register")
    public ResponseEntity<?> register(@RequestBody JsonNode body) {
        jsonSchemaValidator.validateWithJsonSchema(body);
        Request request = objectMapper.convertValue(body, Request.class);

        if(registerUseCase.existsByEmail(request.getUsername())) {
            return new ResponseEntity<>(Map.of(MESSAGE, EMAIL_ALREADY_EXISTS), HttpStatus.CONFLICT);        }
        return ResponseEntity.ok(registerUseCase.register(request));
    }

}
