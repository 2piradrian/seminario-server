package com.group3.catalog.domain.dto.posttype.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.catalog.domain.dto.posttype.request.GetPostTypeListByIdReq;
import com.group3.catalog.domain.dto.posttype.response.GetPostTypeListByIdRes;
import com.group3.entity.PostType;

import java.util.List;
import java.util.Map;

public class GetPostTypeListByIdMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetPostTypeListByIdReq toRequest(Map<String, Object> payload){
        return GetPostTypeListByIdReq.create(
                objectMapper.convertValue(payload.get("ids"), new TypeReference<List<String>>() {})
        );
    }

    public GetPostTypeListByIdRes toResponse(List<PostType> postTypes){
        return new GetPostTypeListByIdRes(
                postTypes
        );
    }
}
