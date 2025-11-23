package com.group3.catalog.domain.dto.posttype.mapper.implementation;

import com.group3.catalog.domain.dto.posttype.request.GetPostTypeByIdReq;
import com.group3.catalog.domain.dto.posttype.response.GetPostTypeByIdRes;
import com.group3.entity.PostType;

public class GetPostTypeByIdMapper {

    public GetPostTypeByIdReq toRequest(String id){
        return GetPostTypeByIdReq.create(
                id
        );
    }

    public GetPostTypeByIdRes toResponse(PostType postType){
        return new GetPostTypeByIdRes(
                postType
        );
    }
}
