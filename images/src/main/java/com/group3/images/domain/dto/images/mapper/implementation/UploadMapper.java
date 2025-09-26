package com.group3.images.domain.dto.images.mapper.implementation;

import com.group3.images.domain.dto.images.request.UploadImageReq;

import java.util.Map;

public class UploadMapper {

    public UploadImageReq toRequest(Map<String, Object> payload, String secret){
        return UploadImageReq.create(
                (String) payload.get("secret"),
                (String) payload.get("base64Image")
        );
    }

}
