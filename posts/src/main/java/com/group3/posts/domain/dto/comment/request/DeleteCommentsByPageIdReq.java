package com.group3.posts.domain.dto.comment.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteCommentsByPageIdReq {
    private final String pageId;
    private final String secret;

    private DeleteCommentsByPageIdReq(String pageId, String secret) {
        this.pageId = pageId;
        this.secret = secret;
    }

    public static DeleteCommentsByPageIdReq create(String pageId, String secret) {
        if (pageId == null || pageId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new DeleteCommentsByPageIdReq(pageId, secret);
    }
}
