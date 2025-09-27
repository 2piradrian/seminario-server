package com.group3.images.domain.dto.images.mapper;

import com.group3.images.domain.dto.images.mapper.implementation.DeleteMapper;
import com.group3.images.domain.dto.images.mapper.implementation.LoadMapper;
import com.group3.images.domain.dto.images.mapper.implementation.UploadMapper;

public class ImagesMapper {

    public static UploadMapper upload(){
        return new UploadMapper();
    }

    public static DeleteMapper delete(){
        return new DeleteMapper();
    }

    public static LoadMapper load(){
        return new LoadMapper();
    }

}