package com.nisum.config;


import com.nisum.model.exception.RequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalException extends RuntimeException {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<Map<String, String>> validationErrors(RequestException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(Map.of("mensaje", ex.getMessage()));
    }


}
