package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.contentType.request.GetContentTypeByIdReq;
import com.group3.catalog.domain.dto.contentType.request.GetContentTypeListByIdReq;
import com.group3.catalog.domain.dto.contentType.response.GetAllContentTypeRes;
import com.group3.catalog.domain.dto.contentType.response.GetContentTypeByIdRes;
import com.group3.catalog.domain.dto.contentType.response.GetContentTypeListByIdRes;

public interface ContentTypeServiceI {

    GetAllContentTypeRes getAll();

    GetContentTypeByIdRes getById(GetContentTypeByIdReq dto);

    GetContentTypeListByIdRes getListById(GetContentTypeListByIdReq dto);

}
