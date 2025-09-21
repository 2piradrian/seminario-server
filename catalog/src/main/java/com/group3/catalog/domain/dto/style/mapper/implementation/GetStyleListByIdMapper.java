package com.group3.catalog.domain.dto.style.mapper.implementation;

import com.group3.catalog.domain.dto.style.response.GetStyleListByIdRes;
import com.group3.entity.Style;

import java.util.List;

public class GetStyleListByIdMapper {

    public GetStyleListByIdRes toResponse(List<Style> styles){
        return new GetStyleListByIdRes(
            styles
        );
    }

}
