package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPostByIdReq {

    private final String postId;

    private final String token;

    private GetPostByIdReq(String postId, String token) {
        this.postId = postId;
        this.token = token;
    }

    public static GetPostByIdReq create(String postId, String token) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (postId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPostByIdReq(postId, token);
    }

}
