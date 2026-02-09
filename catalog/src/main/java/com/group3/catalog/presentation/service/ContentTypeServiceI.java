package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.contentType.request.*;
import com.group3.catalog.domain.dto.contentType.response.*;

public interface ContentTypeServiceI {

    GetAllContentTypeRes getAll();

    GetContentTypeByIdRes getById(GetContentTypeByIdReq dto);

    GetContentTypeListByIdRes getListById(GetContentTypeListByIdReq dto);

    CreateContentTypeRes create(CreateContentTypeReq dto);

    EditContentTypeRes edit(EditContentTypeReq dto);

    void delete(DeleteContentTypeReq dto);

}
