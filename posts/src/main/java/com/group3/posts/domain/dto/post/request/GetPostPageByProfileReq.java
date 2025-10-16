package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPostPageByProfileReq {

    private final Integer page;

    private final Integer size;

    private final String profileId;

    private final String token;

    private GetPostPageByProfileReq(Integer page, Integer size, String profileId, String token) {
        this.page = page;
        this.size = size;
        this.profileId = profileId;
        this.token = token;
    }

    public static GetPostPageByProfileReq create(Integer page, Integer size, String profileId, String token) {

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (page == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (page < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (size == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (size < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (profileId == null || profileId.isBlank()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPostPageByProfileReq(page, size, profileId, token);
    }

}
