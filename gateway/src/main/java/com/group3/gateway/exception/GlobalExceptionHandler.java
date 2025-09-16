package com.twopiradrian.gateway.exception;

import com.twopiradrian.error.ErrorHandler;
import com.twopiradrian.error.ErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorHandler.class)
    public ResponseEntity<?> handleErrorHandler(ErrorHandler e) {
        return ResponseEntity
                .status(e.getHttpCode())
                .body(e.toResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        return ResponseEntity
                .status(500)
                .body(new ErrorHandler(ErrorType.INTERNAL_ERROR).toResponse());
    }

}
