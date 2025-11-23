package com.group3.posts.domain.dto.post.request;

import com.group3.entity.Category;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class EditPostReq {

    private final String token;

    private final String postId;

    private final String title;

    private final String content;

    private final String base64Image;

    private final String postTypeId;

    private EditPostReq(
        String token,
        String postId,
        String title,
        String content,
        String base64Image,
        String postTypeId
    ) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.postId = postId;
        this.base64Image = base64Image;
        this.postTypeId = postTypeId;
    }

    public static EditPostReq create(
        String token,
        String postId,
        String title,
        String content,
        String base64Image,
        String postTypeId
    ) {
        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        title = title.trim();
        if (title.isEmpty() || title.length() > 256) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (content == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        content = content.trim();
        if (content.isEmpty() || content.length() > 4096) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (postTypeId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new EditPostReq(
            token,
            postId,
            title,
            content,
            base64Image,
            postTypeId
        );
    }

}
