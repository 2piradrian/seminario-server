package com.group3.pages.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;

import java.util.List;

public class EditPageReq {
    
    private final String token;

    private final String name;

    private final String base64Image;

    private final String ownerId;

    private final List<String> members;

    private EditPageReq(String token, String name, String base64Image, String ownerId, List<String> members) {
        this.token = token;
        this.name = name;
        this.base64Image = base64Image;
        this.ownerId = ownerId;
        this.members = members;
    }

    public static EditPageReq create(String token, String name, String base64Image, String ownerId, List<String> members) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (name == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        name = name.trim();
        if (name.isEmpty() || name.length() > 64) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (members == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (ownerId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (ownerId.isEmpty()) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new EditPageReq(token, name, base64Image, ownerId, members);
    }
    
}
