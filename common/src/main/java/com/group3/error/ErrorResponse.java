package com.group3.error;

public class ErrorResponse {

    public String message;

    public ErrorResponse(ErrorHandler errorHandler) {
        this.message = errorHandler.getMessage();
    }

}

