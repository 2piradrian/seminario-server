package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class LoginUserReq {

    private final String email;

    private final String password;

    private LoginUserReq(String email, String password){
        this.email = email;
        this.password = password;
    }

    public  static  LoginUserReq create(String email, String password){

        if (password == null || email == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (password.isEmpty() || email.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators passwordValidator = RegexValidators.PASSWORD;
        if (!password.matches(passwordValidator.getRegex())){
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        RegexValidators emailValidator = RegexValidators.EMAIL;
        if (!email.matches(emailValidator.getRegex())){
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new LoginUserReq(email,password);
    }

}
