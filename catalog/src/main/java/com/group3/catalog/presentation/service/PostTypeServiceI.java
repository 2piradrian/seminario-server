package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.posttype.request.*;
import com.group3.catalog.domain.dto.posttype.response.*;

public interface PostTypeServiceI {

    GetAllPostTypeRes getAll();

    GetPostTypeByIdRes getById(GetPostTypeByIdReq dto);

    GetPostTypeListByIdRes getListById(GetPostTypeListByIdReq dto);

    CreatePostTypeRes create(CreatePostTypeReq dto);

    EditPostTypeRes edit(EditPostTypeReq dto);

    void delete(DeletePostTypeReq dto);
}
