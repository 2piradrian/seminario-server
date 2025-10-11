package com.group3.user_profiles.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

    NAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$"),
    SURNAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$"),
    SHORT_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?'\"¡¿@%&$*+_:;\\-\\s]{1,50}$"),
    LONG_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?'\"¡¿@%&$*+_:;\\-\\s]{1,150}$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }

}
