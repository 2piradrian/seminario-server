package com.group3.users.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

    EMAIL("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"),
    NAME("^[\\p{L}]{2,20}(?:[ '-][\\p{L}]{2,20})*$"),
    SURNAME("^[\\p{L}]{2,20}(?:[ '-][\\p{L}]{2,20})*$"),
    PASSWORD("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,}$"),
    SHORT_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 @!|¿?¡%&$*+_:;=/#\\\\-\\\\^~`'\",.]{1,65}$"),
    LONG_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 @!|¿?¡%&$*+_:;=/#\\\\-\\\\^~`'\",.]{1,150}$"),
    COMMENT_CONTENT("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?'\"¡¿@%&$*+_:;\\-\\s]{1,250}$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }

}
