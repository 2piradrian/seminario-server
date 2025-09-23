package com.group3.users.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

    NAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$"),
    SURNAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$"),
    PASSWORD("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,}$"),
    EMAIL("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"),
    SHORT_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?()'\"\\-\\s]{10,50}$"),
    LONG_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?()'\"\\-\\s]{10,150}$"),
    IMAGE_URL("https://[^\\s]+\\.(jpg|jpeg|png|gif|webp|bmp)(\\?.*)?$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }

}
