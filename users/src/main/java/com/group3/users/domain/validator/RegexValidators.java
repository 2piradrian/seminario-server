package com.group3.users.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

  NAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$"),
  SURNAME("^[\\p{L}]+(?:[ '-][\\p{L}]+)*$"),
  PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"),
  EMAIL("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"),
  SHORT_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?()'\"\\-\\s]{10,50}$"),
  LONG_DESCRIPTION("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9.,!?()'\"\\-\\s]{10,150}$");

  private final String regex;

  RegexValidators(String regex) {
    this.regex = regex;
  }

}
