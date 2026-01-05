package com.group3.page_profiles.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

    NAME("^[\\p{L}]{2,20}(?:[ '-][\\p{L}]{2,20})*$"),
    SHORT_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 @!|¿?¡%&$*+_:;=/#\\\\-\\\\^~`'\",.]{1,50}$"),
    LONG_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 @!|¿?¡%&$*+_:;=/#\\\\-\\\\^~`'\",.]{1,150}$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }
    
}
