package com.group3.posts.domain.dto.post.request;


import com.group3.entity.Category;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPostPageReq {

    private final Category category;

    private final Integer page;

    private final Integer size;

    public GetPostPageReq(Category category, Integer page, Integer size) {
        this.category = category;
        this.page = page;
        this.size = size;
    }

    public static GetPostPageReq create(String category, Integer page, Integer size) {

        Category categoryEnum = null;
        if (category != null ) {
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

        return new GetPostPageReq(categoryEnum, page, size);
    }
}
