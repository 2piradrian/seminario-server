package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.page_profiles.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class CreatePageReq {

    private final String token;
    private final String name;
    private final String pageTypeId;

    private CreatePageReq(String token, String name, String pageTypeId) {
        this.token = token;
        this.name = name;
        this.pageTypeId = pageTypeId;
    }

    public static CreatePageReq create(String token, String name, String pageTypeId) {
        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }
        if (name == null || name.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (!name.matches(RegexValidators.NAME.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }
        if (pageTypeId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CreatePageReq(token, name, pageTypeId);
    }
}
