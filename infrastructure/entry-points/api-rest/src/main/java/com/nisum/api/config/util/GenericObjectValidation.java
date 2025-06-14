package com.nisum.api.config.util;

public interface GenericObjectValidation {
    <T> void validateWithJsonSchema(T object);
}
