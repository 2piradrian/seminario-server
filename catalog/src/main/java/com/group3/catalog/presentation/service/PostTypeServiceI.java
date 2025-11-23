package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.posttype.request.GetPostTypeByIdReq;
import com.group3.catalog.domain.dto.posttype.request.GetPostTypeListByIdReq;
import com.group3.catalog.domain.dto.posttype.response.GetAllPostTypeRes;
import com.group3.catalog.domain.dto.posttype.response.GetPostTypeByIdRes;
import com.group3.catalog.domain.dto.posttype.response.GetPostTypeListByIdRes;

public interface PostTypeServiceI {

    GetAllPostTypeRes getAll();

    GetPostTypeByIdRes getById(GetPostTypeByIdReq dto);

    GetPostTypeListByIdRes getListById(GetPostTypeListByIdReq dto);
}
