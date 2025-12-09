package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class RecoverPasswordReq {

    private final String email;

    private RecoverPasswordReq(String email){
        this.email = email;
    }

    public static RecoverPasswordReq create(String email) {

        if (email == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators emailValidator = RegexValidators.EMAIL;
        if (!email.matches(emailValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new RecoverPasswordReq(email);

    }

}
