package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeletePostsByUserIdReq {
    private final String userId;
    private final String secret;

    private DeletePostsByUserIdReq(String userId, String secret) {
        this.userId = userId;
        this.secret = secret;
    }

    public static DeletePostsByUserIdReq create(String userId, String secret) {
        if (userId == null || userId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new DeletePostsByUserIdReq(userId, secret);
    }
}
