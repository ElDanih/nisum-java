package com.nisum.api.config.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;


@Log4j2
@RequiredArgsConstructor
public class JsonSchemaValidator {

    private final JsonSchema jsonSchema;

    public void validate(JsonNode body) {
        try {
            ProcessingReport report = jsonSchema.validate(body);
            log.info(report.toString());
            if (!report.isSuccess()) {
                System.out.printf("Validation failed: %s%n", report);
                //report.iterator().forEachRemaining(m -> errors.addMessage(m.asJson()));
            }
        } catch (ProcessingException e) {
            log.error(e);
            System.out.printf("Validation error: %s%n", e.getShortMessage());
            //errors.addMessage(e.getShortMessage());
        }
    }
}

