package com.nisum.api.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.nisum.api.config.util.JsonSchemaValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Objects;

@Configuration
public class JsonSchemaConfiguration {

    @Value("${schema.email-pattern}")
    private String emailPattern;

    @Value("${schema.password-pattern}")
    private String passwordPattern;

    public JsonSchema jsonSchema(String schemaFileName) throws ProcessingException, IOException {
        var factory = JsonSchemaFactory.byDefault();
        var path = this.getClass().getResource("/schemas/" + schemaFileName);
        JsonNode jsonSchemaNode = JsonLoader.fromURL(Objects.requireNonNull(path));
        configureJsonSchema(jsonSchemaNode);
        return factory.getJsonSchema(jsonSchemaNode);
    }

    @Bean
    public JsonSchemaValidator jsonSchemaRequest() throws IOException, ProcessingException {
        return new JsonSchemaValidator(jsonSchema( "request_schema.json"));
    }

    private void configureJsonSchema(JsonNode jsonSchemaNode) {
        ObjectNode objectNode = (ObjectNode) jsonSchemaNode;
        ObjectNode properties = (ObjectNode) objectNode.get("properties");
        if (properties != null) {
            if (properties.has("email")) {
                ((ObjectNode) properties.get("email")).put("pattern", emailPattern);
            }
            if (properties.has("password")) {
                ((ObjectNode) properties.get("password")).put("pattern", passwordPattern);
            }
        }
    }
}
