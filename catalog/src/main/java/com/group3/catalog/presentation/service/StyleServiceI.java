package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.style.request.GetStyleByIdReq;
import com.group3.catalog.domain.dto.style.request.GetStyleListByIdReq;
import com.group3.catalog.domain.dto.style.response.GetAllStyleRes;
import com.group3.catalog.domain.dto.style.response.GetStyleByIdRes;
import com.group3.catalog.domain.dto.style.response.GetStyleListByIdRes;

public interface StyleServiceI {

    GetAllStyleRes getAll();

    GetStyleByIdRes getById(GetStyleByIdReq dto);

    GetStyleListByIdRes getListById(GetStyleListByIdReq dto);

}
