package com.group3.posts.domain.dto.comment.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreateCommentReq {

    private final String token;

    private final String postId;

    private final String content;

    private final String replyTo;

    public CreateCommentReq(String token, String postId, String content, String replyTo) {
        this.token = token;
        this.postId = postId;
        this.content = content;
        this.replyTo = replyTo;
    }

    public static CreateCommentReq create(String token, String postId, String content, String replyTo) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (postId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (content == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        content = content.trim();
        if (content.isEmpty() || content.length() > 2048) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new CreateCommentReq(token, postId, content, replyTo);
    }
}
