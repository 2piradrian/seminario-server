package com.group3.error;

import lombok.Getter;

@Getter
public enum ErrorType {

    MISSING_REQUIRED_FIELDS("Missing required fields", 400),
    INVALID_FIELDS("Invalid fields", 400),

    USER_NOT_ACTIVATED("User not activated", 400),
    USER_DELETED("User has been banned", 400),
    PASSWORDS_DO_NOT_MATCH("Passwords do not match", 400),
    USER_ALREADY_ACTIVATED("User already activated", 400),

    USER_NOT_FOUND("User not found", 404),
    POST_NOT_FOUND("Forum not found", 404),

    UNAUTHORIZED("Unauthorized", 401),
    INVALID_PASSWORD("Invalid password", 400),

    FULLNAME_ALREADY_EXISTS("Full name of the user already exists", 400),
    EMAIL_ALREADY_EXISTS("Email already exists", 400),

    POST_NOT_ACTIVE("Forum not active", 400),
    COMMENT_NOT_FOUND("Comment not found", 404),

    STYLE_NOT_FOUND("Style not found", 404),
    INSTRUMENT_NOT_FOUND("Instrument not found", 404),

    INTERNAL_ERROR("Internal error", 500);

    private final String message;
    private final int httpCode;

    ErrorType(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

}
