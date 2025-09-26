package com.group3.images.domain.dto.images.mapper.implementation;

import com.group3.images.domain.dto.images.request.DeleteImageReq;

import java.util.Map;

public class DeleteMapper {

    public DeleteImageReq toRequest(Map<String, Object> payload){
        return DeleteImageReq.create(
                (String) payload.get("secret"),
                (String) payload.get("imageId")
        );
    }

}
