package com.group3.catalog.domain.dto.pagetype.mapper.implementation;

import com.group3.catalog.domain.dto.pagetype.response.GetAllPageTypeRes;
import com.group3.entity.PageType;

import java.util.List;

public class GetAllPageTypeMapper {

    public GetAllPageTypeRes toResponse(List<PageType> pageType){
        return new GetAllPageTypeRes(
                pageType
        );
    }
}
