package com.group3.catalog.domain.dto.style.mapper.implementation;

import com.group3.catalog.domain.dto.style.response.GetAllStyleRes;
import com.group3.entity.Style;

import java.util.List;

public class GetAllStyleMapper {

    public GetAllStyleRes toResponse(List<Style> styles){
        return new GetAllStyleRes(
            styles
        );
    }

}
