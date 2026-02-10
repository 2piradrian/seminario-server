package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeletePostReq {

    private final String token;

    private final String postId;

    private final String reasonId;

    private DeletePostReq(String token, String postId, String reasonId) {
        this.token = token;
        this.postId = postId;
        this.reasonId = reasonId;
    }

    public static DeletePostReq create(String token, String postId, String reasonId) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (postId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeletePostReq(token, postId, reasonId);
    }

}
