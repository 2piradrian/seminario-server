package com.group3.catalog.domain.dto.style.mapper;

import com.group3.catalog.domain.dto.style.mapper.implementation.GetAllStyleMapper;
import com.group3.catalog.domain.dto.style.mapper.implementation.GetStyleByIdMapper;
import com.group3.catalog.domain.dto.style.mapper.implementation.GetStyleListByIdMapper;

public class StyleMapper {

    public static GetStyleByIdMapper getById() {
        return new GetStyleByIdMapper();
    }

    public static GetStyleListByIdMapper getListById() {
        return new GetStyleListByIdMapper();
    }

    public static GetAllStyleMapper getAll() {
        return new GetAllStyleMapper();
    }

}
