package com.group3.posts.domain.dto.comment.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetCommentByIdReq {
    private final String commentId;
    private final String token;

    private GetCommentByIdReq(String commentId, String token) {
        this.commentId = commentId;
        this.token = token;
    }

    public static GetCommentByIdReq create(String commentId, String token) {
        if (commentId == null || token == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetCommentByIdReq(commentId, token);
    }
}
