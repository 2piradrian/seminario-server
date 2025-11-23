package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFilteredPostPageReq {

    private final String token;

    private final Integer page;

    private final Integer size;

    private final String text;

    private final String postTypeId;

    private final String secret;

    private GetFilteredPostPageReq(String token, Integer page, Integer size, String text, String postTypeId, String secret) {
        this.token = token;
        this.page = page;
        this.size = size;
        this.text = text;
        this.secret = secret;
        this.postTypeId = postTypeId;
    }

    public static GetFilteredPostPageReq create(String token, Integer page, Integer size, String text, String postTypeId, String secret) {

        if (secret == null || secret.isBlank()) {
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

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new GetFilteredPostPageReq(token, page, size,text, postTypeId, secret);
    }

}
