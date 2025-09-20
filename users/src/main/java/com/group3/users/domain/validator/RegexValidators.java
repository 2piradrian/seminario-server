package com.group3.users.domain.validator;

import lombok.Getter;

@Getter
public enum RegexValidators {

  NAME("^[A-Za-zÁÉÍÓÚáéíóúÑñ][A-Za-zÁÉÍÓÚáéíóúÑñ ']{1,49}$"),
  SURNAME("^[A-Za-zÁÉÍÓÚáéíóúÑñ][A-Za-zÁÉÍÓÚáéíóúÑñ ']{1,49}$"),
  PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"),
  EMAIL("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

  private final String regex;

  RegexValidators(String regex) {
    this.regex = regex;
  }

}
