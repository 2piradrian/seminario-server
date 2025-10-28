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
    USER_ALREADY_HAS_NO_ROLE("User already has no assigned role", 400),
    USER_ALREADY_HAS_ROLE("User already has assigned role", 400),
    USER_ALREADY_IS_AUTHOR("User already is the event author", 400),
    EVENT_NOT_STARTED("Event is not started", 400),
    EVENT_ALREADY_ENDED("Event is already ended", 400),

    USER_NOT_FOUND("User not found", 404),
    POST_NOT_FOUND("Forum not found", 404),
    PAGE_NOT_FOUND("Page not found", 404),
    PAGE_TYPE_NOT_FOUND("Page type not found", 404),
    EVENT_NOT_FOUND("Event not found", 404),
    UNAUTHORIZED("Unauthorized", 401),
    INVALID_PASSWORD("Invalid password", 400),
    FULLNAME_ALREADY_EXISTS("Full name of the user already exists", 400),
    EMAIL_ALREADY_EXISTS("Email already exists", 400),
    POST_NOT_ACTIVE("Post not active", 400),
    COMMENT_NOT_FOUND("Comment not found", 404),
    STYLE_NOT_FOUND("Style not found", 404),
    ROLE_NOT_FOUND("Role not found", 404),
    INSTRUMENT_NOT_FOUND("Instrument not found", 404),
    CATEGORY_NOT_FOUND("Category not found", 404),
    PAGETYPE_NOT_FOUND("Page type not found", 404),
    PAGENAME_ALREADY_EXISTS("Page name already exists", 400),
    CONTENT_TYPE_NOT_FOUND("Content type not found", 404),

    INTERNAL_ERROR("Internal error", 500);

    private final String message;
    private final int httpCode;

    ErrorType(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

}
