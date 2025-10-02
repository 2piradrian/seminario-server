package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeByIdReq;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeListByIdReq;
import com.group3.catalog.domain.dto.pagetype.response.GetAllPageTypeRes;
import com.group3.catalog.domain.dto.pagetype.response.GetPageTypeByIdRes;
import com.group3.catalog.domain.dto.pagetype.response.GetPageTypeListByIdRes;

public interface PageTypeServiceI {

    GetAllPageTypeRes getAll();

    GetPageTypeByIdRes getById(GetPageTypeByIdReq dto);

    GetPageTypeListByIdRes getListById(GetPageTypeListByIdReq dto);
}
