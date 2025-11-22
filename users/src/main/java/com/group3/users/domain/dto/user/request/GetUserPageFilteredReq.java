package com.group3.users.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetUserPageFilteredReq {

    private final String token;

    private final String secret;

    private final Integer page;

    private final Integer size;

    private final String fullname;

    private final List<String> styles;

    private final List<String> instruments;

    private GetUserPageFilteredReq(String token, String secret, Integer page, Integer size, String fullname, List<String> styles, List<String> instruments) {
        this.token = token;
        this.secret = secret;
        this.page = page;
        this.size = size;
        this.fullname = fullname;
        this.styles = styles;
        this.instruments = instruments;
    }

    public static GetUserPageFilteredReq create(String token, String secret, Integer page, Integer size, String fullname, List<String> styles, List<String> instruments) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (secret == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (page == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (page < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (size == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (size < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new GetUserPageFilteredReq(token, secret, page, size, fullname, styles, instruments);
    }

}
