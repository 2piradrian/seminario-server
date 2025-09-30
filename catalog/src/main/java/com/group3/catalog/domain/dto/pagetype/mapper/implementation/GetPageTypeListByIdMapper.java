package com.group3.catalog.domain.dto.pagetype.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeByIdReq;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeListByIdReq;
import com.group3.catalog.domain.dto.pagetype.response.GetPageTypeListByIdRes;
import com.group3.entity.PageType;

import java.util.List;
import java.util.Map;

public class GetPageTypeListByIdMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetPageTypeListByIdReq toRequest(Map<String, Object> payload){
        return GetPageTypeByIdReq.create(
                objectMapper.convertValue(payload.get("ids"), new TypeReference<List<String>>() {})
        );
    }

    public GetPageTypeListByIdRes toResponse(List<PageType> pageTypes){
        return new GetPageTypeListByIdRes(
                pageTypes
        );
    }
}
