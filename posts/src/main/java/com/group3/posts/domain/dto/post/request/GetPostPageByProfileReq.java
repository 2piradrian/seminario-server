package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPostPageByProfileReq {

    private final Integer page;
    private final Integer size;
    private final String profileId;

    public GetPostPageByProfileReq(Integer page, Integer size, String profileId) {
        this.page = page;
        this.size = size;
        this.profileId = profileId;
    }

    public static GetPostPageByProfileReq create(Integer page, Integer size, String profileId) {

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

        return new GetPostPageByProfileReq(page, size, profileId);
    }

}
