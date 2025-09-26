package com.group3.images.presentation.service;

import com.group3.images.domain.dto.images.request.DeleteImageReq;
import com.group3.images.domain.dto.images.request.LoadImageReq;
import com.group3.images.domain.dto.images.request.UploadImageReq;
import com.group3.images.domain.dto.images.response.UploadImageRes;
import org.springframework.core.io.Resource;

public interface ImageServiceI {

    UploadImageRes uploadImage(UploadImageReq dto);

    void deleteImage(DeleteImageReq dto);

    Resource loadImage(LoadImageReq dto);

}
