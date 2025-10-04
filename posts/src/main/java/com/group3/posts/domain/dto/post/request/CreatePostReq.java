package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreatePostReq {

    private final String token;

    private final String title;

    private final String content;

    private final String profileId;

    private final String image;

    private CreatePostReq(String token, String title, String content, String profileId, String image) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.profileId = profileId;
        this.image = image;
    }

    public static CreatePostReq create(String token, String title, String content, String profileId, String image) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (title == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        title = title.trim();
        if (title.isEmpty() || title.length() > 64) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (content == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        content = content.trim();
        if (content.isEmpty() || content.length() > 4096) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (profileId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CreatePostReq(token, title, content, profileId, image);
    }

}
