package com.group3.posts.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

    COMMENT_CONTENT("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?'\"¡¿@%&$*+_:;\\s\\[\\]{}-]{1,250}$"),
    TITLE("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?'\"¡¿@%&$*+_:;\\s\\[\\]{}-]{1,64}$"),
    CONTENT("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?'\"¡¿@%&$*+_=#/?;:\\\\s\\r\\n\\[\\]{}-]{1,4096}$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }

}
