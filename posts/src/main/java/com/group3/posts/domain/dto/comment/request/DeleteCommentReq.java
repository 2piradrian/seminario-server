package com.group3.posts.domain.dto.comment.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteCommentReq {

    private final String token;

    private final String commentId;

    private final String reasonId;

    public DeleteCommentReq(String token, String commentId, String reasonId) {
        this.token = token;
        this.commentId = commentId;
        this.reasonId = reasonId;
    }

    public static DeleteCommentReq create(String token, String commentId, String reasonId) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (commentId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeleteCommentReq(token, commentId, reasonId);
    }
}
