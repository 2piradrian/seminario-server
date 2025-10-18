package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFilteredPostPageReq {

    private final Integer page;

    private final Integer size;

    private final List<String> ids;

    private final String text;

    private final String secret;

    private GetFilteredPostPageReq(Integer page, Integer size, List<String> ids, String text, String secret) {
        this.page = page;
        this.size = size;
        this.ids = ids;
        this.text = text;
        this.secret = secret;
    }

    public static GetFilteredPostPageReq create(Integer page, Integer size, List<String> ids, String text, String secret) {

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

        return new GetFilteredPostPageReq(page, size, ids,text, secret);
    }

}
