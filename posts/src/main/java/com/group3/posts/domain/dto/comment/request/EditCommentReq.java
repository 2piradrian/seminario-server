package com.group3.posts.domain.dto.comment.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class EditCommentReq {

    private final String token;

    private final String commentId;

    private final String content;

    public EditCommentReq(String token, String commentId, String content) {
        this.token = token;
        this.commentId = commentId;
        this.content = content;
    }

    public static EditCommentReq create(String token, String commentId, String content) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (commentId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (content == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        content = content.trim();
        if (content.isEmpty() || content.length() > 2048) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new EditCommentReq(token, commentId, content);
    }
}
