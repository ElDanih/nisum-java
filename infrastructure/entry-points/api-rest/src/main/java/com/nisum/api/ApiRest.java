package com.nisum.api;
import com.fasterxml.jackson.databind.JsonNode;
import com.nisum.api.config.util.JsonSchemaValidator;
import com.nisum.model.dto.RequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private final ApiRestService apiRestService;
    private final JsonSchemaValidator jsonSchemaValidator;

    @GetMapping(path = "/usecase/path")
    public String commandName() {

        return "Hello world!";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody JsonNode body) {
        jsonSchemaValidator.validate(body);
        return null;
    }
}
