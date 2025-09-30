package com.group3.posts.domain.dto.post.request;

import com.group3.entity.Category;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class CreatePostReq {

    private final String token;

    private final String title;

    private final String content;

    private final String pageId;

    private final Category category;

    private final String base64Image;

    private CreatePostReq(String token, String title, String content, String pageId, Category category, String base64Image) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.pageId = pageId;
        this.category = category;
        this.base64Image = base64Image;
    }

    public static CreatePostReq create(String token, String title, String content, String pageId, Category category, String base64Image) {

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

        if (category == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new CreatePostReq(token, title, content, pageId, category, base64Image);
    }

}
