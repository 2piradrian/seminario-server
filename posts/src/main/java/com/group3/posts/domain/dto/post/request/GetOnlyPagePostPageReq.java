package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetOnlyPagePostPageReq {

    private final Integer page;

    private final Integer size;

    private final String token;

    private final String secret;

    private GetOnlyPagePostPageReq(Integer page, Integer size, String token, String secret) {
        this.page = page;
        this.size = size;
        this.token = token;
        this.secret = secret;
    }

    public static GetOnlyPagePostPageReq create(Integer page, Integer size, String token, String secret) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (secret == null || secret.isEmpty()){
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

        return new GetOnlyPagePostPageReq(page, size, token, secret);
    }

}
