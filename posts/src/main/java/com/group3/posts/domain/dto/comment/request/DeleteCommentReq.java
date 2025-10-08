package com.group3.posts.domain.dto.comment.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteCommentReq {

    private final String token;

    private final String commentId;

    public DeleteCommentReq(String token, String commentId) {
        this.token = token;
        this.commentId = commentId;
    }

    public static DeleteCommentReq create(String token, String commentId) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (commentId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeleteCommentReq(token, commentId);
    }
}
