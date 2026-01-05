package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class ChangePasswordReq {

    private final String token;

    private final String newPassword;

    private ChangePasswordReq(String token, String newPassword){
        this.token = token;
        this.newPassword = newPassword;
    }

    public static ChangePasswordReq create(String token, String newPassword) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (newPassword == null || newPassword.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators passwordValidator = RegexValidators.PASSWORD;
        if (!newPassword.matches(passwordValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new ChangePasswordReq(token, newPassword);

    }

}
