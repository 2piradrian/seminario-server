package com.group3.pages.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {
    
    NAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$");

    private final String regex;

    RegexValidators(String regex) {
        this.regex = regex;
    }
    
}
