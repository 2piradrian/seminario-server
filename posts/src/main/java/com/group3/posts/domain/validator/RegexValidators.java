package com.group3.posts.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

    USERNAME("^[a-zA-Z0-9]{3,20}$"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"),
    EMAIL("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }

}
