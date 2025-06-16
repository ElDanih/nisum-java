package com.nisum.api.config.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.nisum.model.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {
    private final List<String> messages;

    private ErrorCode errorCode;

    public ValidationErrors() {
        messages = new ArrayList<>();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }


    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(JsonNode json) {
        errorCode = ErrorCode.ERROR_400;
        messages.add("Error de Formato Valide la firma");
    }

    public void addMessage(String string) {
        messages.add(string);
    }

    public boolean isInError() {
        return !messages.isEmpty();
    }

}
