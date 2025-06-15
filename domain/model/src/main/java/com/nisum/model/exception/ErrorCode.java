package com.nisum.model.exception;

public enum ErrorCode {
    ERROR_400(400, "Error en la firma"),
    ERROR_409(409, "El correo ya esta registrado"),
    ERROR_500(500, "Error de conexion con la base de datos");

    private int errorCode;
    private String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
