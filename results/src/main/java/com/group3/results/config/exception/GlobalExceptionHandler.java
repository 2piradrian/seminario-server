package com.group3.results.config.exception;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorHandler.class)
    public ResponseEntity<?> handleErrorHandler(ErrorHandler e) {
        log.error("Unhandled exception", e);
        return ResponseEntity
                .status(e.getHttpCode())
                .body(e.toResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        log.error("Unhandled exception", e);
        return ResponseEntity
                .status(500)
                .body(new ErrorHandler(ErrorType.INTERNAL_ERROR).toResponse());
    }

}
