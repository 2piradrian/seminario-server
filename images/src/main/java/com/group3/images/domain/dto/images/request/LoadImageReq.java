package com.group3.images.domain.dto.images.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class LoadImageReq {

    private final String imageId;

    private LoadImageReq(String imageId) {
        this.imageId = imageId;
    }

    public static LoadImageReq create(String imageId) {

        if (imageId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (imageId.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new LoadImageReq(imageId);
    }

}
