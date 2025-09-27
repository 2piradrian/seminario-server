package com.group3.images.domain.dto.images.mapper.implementation;

import com.group3.images.domain.dto.images.request.LoadImageReq;

public class LoadMapper {

    public LoadImageReq toRequest(String imageId){
        return LoadImageReq.create(
                imageId
        );
    }

}
