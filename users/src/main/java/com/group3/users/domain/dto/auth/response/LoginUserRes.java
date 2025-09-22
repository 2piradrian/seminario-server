package com.group3.users.domain.dto.auth.response;

import com.group3.entity.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserRes {

    private final Token token;

}
