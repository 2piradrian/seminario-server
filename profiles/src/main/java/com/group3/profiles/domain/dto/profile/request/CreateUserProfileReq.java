package com.group3.profiles.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreateUserProfileReq {

    private final String id;

    private final String email;

    private final String name;

    private final String surname;

    private final String secret;

    private CreateUserProfileReq(String id, String email, String name, String surname, String secret) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.secret = secret;
    }

    public static CreateUserProfileReq create(String id, String email, String name, String surname, String secret) {

        if (id == null || email == null || name == null || surname == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (secret == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (name.isEmpty() || surname.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CreateUserProfileReq(id, email, name, surname, secret);
    }

}
