package com.nisum.api.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.nisum.api.config.util.JsonSchemaValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Objects;

@Configuration
public class JsonSchemaConfiguration {

    public JsonSchema jsonSchema(String schemaFileName) throws ProcessingException, IOException {
        var factory = JsonSchemaFactory.byDefault();
        var path = this.getClass().getResource("/schemas/" + schemaFileName);
        JsonNode jsonSchemaNode = JsonLoader.fromURL(Objects.requireNonNull(path));
        return factory.getJsonSchema(jsonSchemaNode);
    }

    @Bean
    public JsonSchemaValidator jsonSchemaVinculation() throws IOException, ProcessingException {
        return new JsonSchemaValidator(jsonSchema( "request_schema.json"));
    }
}
