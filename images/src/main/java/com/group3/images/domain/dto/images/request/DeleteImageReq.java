package com.group3.images.domain.dto.images.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class DeleteImageReq {

    private final String secret;

    private final String imageId;

    private DeleteImageReq(String secret, String imageId) {
        this.secret = secret;
        this.imageId = imageId;
    }

    public static DeleteImageReq create(String secret, String imageId) {

        if (imageId == null || secret == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (imageId.isEmpty() || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new DeleteImageReq(secret, imageId);
    }

}
