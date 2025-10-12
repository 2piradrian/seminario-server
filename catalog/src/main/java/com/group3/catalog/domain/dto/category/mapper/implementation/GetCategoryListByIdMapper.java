package com.group3.catalog.domain.dto.category.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.catalog.domain.dto.category.request.GetCategoryByIdReq;
import com.group3.catalog.domain.dto.category.request.GetCategoryListByIdReq;
import com.group3.catalog.domain.dto.category.response.GetCategoryListByIdRes;
import com.group3.entity.Category;

import java.util.List;
import java.util.Map;

public class GetCategoryListByIdMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetCategoryListByIdReq toRequest(Map<String, Object> payload){
        return GetCategoryListByIdReq.create(
                objectMapper.convertValue(payload.get("ids"), new TypeReference<List<String>>() {})
        );
    }

    public GetCategoryListByIdRes toResponse(List<Category> categories){
        return new GetCategoryListByIdRes(
                categories
        );
    }

}
