package com.group3.posts.domain.dto.comment.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class CreateCommentReq {

    private final String token;

    private final String postId;

    private final String content;

    private final String profileId;

    private final String replyTo;

    public CreateCommentReq(String token, String postId, String content, String profileId, String replyTo) {
        this.token = token;
        this.postId = postId;
        this.content = content;
        this.profileId = profileId;
        this.replyTo = replyTo;
    }

    public static CreateCommentReq create(String token, String postId, String content, String profileId, String replyTo) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (postId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (profileId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (content == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        content = content.trim();
        if (content.isEmpty() || !content.matches(RegexValidators.COMMENT_CONTENT.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new CreateCommentReq(token, postId, content, profileId, replyTo);
    }
}
