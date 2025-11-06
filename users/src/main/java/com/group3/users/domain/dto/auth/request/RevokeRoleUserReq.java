package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class RevokeRoleUserReq {

    private final String token;

    private final String email;

    private RevokeRoleUserReq(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public static RevokeRoleUserReq create(String token, String email) {

        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (email == null || email.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators emailValidator = RegexValidators.EMAIL;
        if (!email.matches(emailValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new RevokeRoleUserReq(token, email);
    }
}
