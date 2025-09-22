package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class RegisterUserReq {

    private final String name;

    private final String surname;

    private final String password;

    private final String email;

    private RegisterUserReq(String name, String surname, String password, String email){
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    public static RegisterUserReq create(String name, String surname, String password, String email) {

        if (name == null || surname == null || password == null || email == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (name.isEmpty() || surname.isEmpty() || password.isEmpty() || email.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators nameValidator = RegexValidators.NAME;
        if (!name.matches(nameValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        RegexValidators surnameValidator = RegexValidators.SURNAME;
        if (!name.matches(surnameValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        RegexValidators passwordValidator = RegexValidators.PASSWORD;
        if (!password.matches(passwordValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        RegexValidators emailValidator = RegexValidators.EMAIL;
        if (!email.matches(emailValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new RegisterUserReq(name, surname, password, email);
    }

}
