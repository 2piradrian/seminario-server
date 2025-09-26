package com.group3.images.domain.dto.images.mapper.implementation;

import com.group3.images.domain.dto.images.request.DeleteImageReq;

public class DeleteMapper {

    public DeleteImageReq toRequest(String imageId, String secret){
        return DeleteImageReq.create(
                secret,
                imageId
        );
    }

}
