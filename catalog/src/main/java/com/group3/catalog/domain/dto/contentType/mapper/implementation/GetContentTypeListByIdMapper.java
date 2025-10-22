package com.group3.catalog.domain.dto.contentType.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.catalog.domain.dto.contentType.request.GetContentTypeListByIdReq;
import com.group3.catalog.domain.dto.contentType.response.GetContentTypeListByIdRes;
import com.group3.entity.ContentType;

import java.util.List;
import java.util.Map;

public class GetContentTypeListByIdMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetContentTypeListByIdReq toRequest(Map<String, Object> payload){
        return GetContentTypeListByIdReq.create(
                objectMapper.convertValue(payload.get("ids"), new TypeReference<List<String>>() {})
        );
    }

    public GetContentTypeListByIdRes toResponse(List<ContentType> contentTypes){
        return new GetContentTypeListByIdRes(
            contentTypes
        );
    }

}
