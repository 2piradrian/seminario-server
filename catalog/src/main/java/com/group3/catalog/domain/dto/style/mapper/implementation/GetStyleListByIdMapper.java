package com.group3.catalog.domain.dto.style.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.catalog.domain.dto.style.request.GetStyleListByIdReq;
import com.group3.catalog.domain.dto.style.response.GetStyleListByIdRes;
import com.group3.entity.Style;

import java.util.List;
import java.util.Map;

public class GetStyleListByIdMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetStyleListByIdReq toRequest(Map<String, Object> payload){
        return GetStyleListByIdReq.create(
                objectMapper.convertValue(payload.get("ids"), new TypeReference<List<String>>() {})
        );
    }

    public GetStyleListByIdRes toResponse(List<Style> styles){
        return new GetStyleListByIdRes(
            styles
        );
    }

}
