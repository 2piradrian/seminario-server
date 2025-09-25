package com.group3.profiles.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

    NAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$"),
    SURNAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$"),
    PASSWORD("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,}$"),
    EMAIL("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"),
    SHORT_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?()'\"\\-\\s]{0,50}$"),
    LONG_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?()'\"\\-\\s]{0,150}$"),
    IMAGE_URL("^https?://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}[^\\s]*$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }

}
