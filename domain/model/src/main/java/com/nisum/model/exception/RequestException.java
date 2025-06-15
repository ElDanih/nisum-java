package com.nisum.model.exception;

public class RequestException extends RuntimeException {

    private final int code;
    private final String message;

    private static final long serialVersionUID = 1L;

    public RequestException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
