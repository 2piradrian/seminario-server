package com.group3.posts.domain.dto.post.request;

import com.group3.entity.PostType;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.domain.validator.RegexValidators;
import lombok.Getter;

@Getter
public class CreatePostReq {

    private final String token;

    private final String title;

    private final String content;

    private final String profileId;

    private final String image;

    private final String postTypeId;

    private CreatePostReq(String token, String title, String content, String profileId, String image, String postTypeId) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.profileId = profileId;
        this.image = image;
        this.postTypeId = postTypeId;
    }

    public static CreatePostReq create(String token, String title, String content, String profileId, String image, String postTypeId) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (title == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        title = title.trim();
        if (title.isEmpty() || !title.matches(RegexValidators.TITLE.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (content == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        content = content.trim();
        if (content.isEmpty() || !content.matches(RegexValidators.CONTENT.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (profileId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (postTypeId == null || postTypeId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CreatePostReq(token, title, content, profileId, image, postTypeId);
    }

}
