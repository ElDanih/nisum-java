package com.nisum.api.config.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.nisum.model.exception.ErrorCode;
import com.nisum.model.exception.RequestException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonSchemaValidator {

    private final JsonSchema jsonSchema;

    public void validate(JsonNode body, ValidationErrors errors) {
        try {
            ProcessingReport report = jsonSchema.validate(body);
            if (!report.isSuccess()) {
                report.iterator().forEachRemaining(m -> errors.addMessage(m.asJson()));
            }
        } catch (ProcessingException e) {
            errors.addMessage(e.getShortMessage());
        }
    }

    public void validateWithJsonSchema(JsonNode jsonNode) {
        ValidationErrors errors = new ValidationErrors();
        this.validate(jsonNode, errors);
        if (errors.isInError()) {
            throw new RequestException(ErrorCode.ERROR_400.getErrorCode(), ErrorCode.ERROR_400.getErrorMessage());
        }

    }
}

