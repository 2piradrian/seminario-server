package com.group3.images.domain.dto.images.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class UploadImageReq {

    private final String secret;

    private final String base64Image;

    private UploadImageReq(String base64Image, String secret) {
        this.base64Image = base64Image;
        this.secret = secret;
    }

    public static UploadImageReq create(String base64Image, String secret) {

        if (base64Image == null || secret == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (base64Image.isEmpty() || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new UploadImageReq(base64Image, secret);
    }

}
