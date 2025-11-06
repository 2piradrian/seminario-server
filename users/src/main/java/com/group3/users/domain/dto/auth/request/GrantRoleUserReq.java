package com.group3.users.domain.dto.auth.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class GrantRoleUserReq {

    private final String token;

    private final String email;

    private final String roleId;

    private GrantRoleUserReq(String token, String email, String roleId){
        this.token = token;
        this.email = email;
        this.roleId = roleId;
    }

    public static GrantRoleUserReq create(String token, String email, String roleId) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (email == null || email.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators emailValidator = RegexValidators.EMAIL;
        if (!email.matches(emailValidator.getRegex())){
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (roleId == null || roleId.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GrantRoleUserReq(token, email, roleId);

    }

}
