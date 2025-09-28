package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPostByIdReq {

    private  final String postId;

    private GetPostByIdReq(String postId) {this.postId = postId;}

    public static GetPostByIdReq create(String postId) {

        if(postId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPostByIdReq(postId);
    }
}
