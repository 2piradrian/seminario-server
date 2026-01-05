package com.group3.events.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

    CONTENT("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?'\"¡¿@%&$*+_=#/?;:\\\\\\-\\s\\r\\n]{1,4096}$"),
    TITLE("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?'\"¡¿@%&$*+_:;\\-\\s]{1,64}$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }

}
