package com.group3.posts.domain.dto.comment.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetCommentPageReq {

    private final String postId;

    private final Integer page;

    private final Integer size;

    public GetCommentPageReq(String postId, Integer page, Integer size) {
        this.postId = postId;
        this.page = page;
        this.size = size;
    }

    public static GetCommentPageReq create(String postId, Integer size, Integer page) {

        if (postId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
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

        return new GetCommentPageReq(postId, size, page);
    }
}
