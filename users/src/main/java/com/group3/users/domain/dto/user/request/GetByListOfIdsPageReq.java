package com.group3.users.domain.dto.user.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetByListOfIdsPageReq {

    private final String token;

    private final String secret;

    private final Integer page;

    private final Integer size;

    private final List<String> ids;

    private GetByListOfIdsPageReq(String token, String secret, Integer page, Integer size, List<String> ids) {
        this.token = token;
        this.secret = secret;
        this.page = page;
        this.size = size;
        this.ids = ids;
    }

    public static GetByListOfIdsPageReq create(String token, String secret, Integer page, Integer size, List<String> ids) {

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

        return new GetByListOfIdsPageReq(token, secret, page, size, ids);
    }

}
