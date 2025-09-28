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

    private final Category category;

    public EditPostReq(String token, String postId, String title, String content, Category category) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.category = category;
        this.postId = postId;
    }

    public static EditPostReq create(String token, String postId, String title, String content, Category category) {
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

        if (category == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        Category categoryEnum = null;
        boolean isValidCategory = false;
        for (Category c : Category.values()) {
            if (c.name().equals(category)) {
                isValidCategory = true;
                categoryEnum = c;
                break;
            }
        }
        if (!isValidCategory) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new EditPostReq(token, postId, title, content, categoryEnum);
    }

}
