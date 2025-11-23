package com.group3.catalog.domain.dto.posttype.mapper.implementation;

import com.group3.catalog.domain.dto.posttype.response.GetAllPostTypeRes;
import com.group3.entity.PostType;

import java.util.List;

public class GetAllPostTypeMapper {

    public GetAllPostTypeRes toResponse(List<PostType> postType){
        return new GetAllPostTypeRes(
                postType
        );
    }
}
