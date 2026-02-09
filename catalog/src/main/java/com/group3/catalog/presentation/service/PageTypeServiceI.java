package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.pagetype.request.*;
import com.group3.catalog.domain.dto.pagetype.response.*;

public interface PageTypeServiceI {

    GetAllPageTypeRes getAll();

    GetPageTypeByIdRes getById(GetPageTypeByIdReq dto);

    GetPageTypeListByIdRes getListById(GetPageTypeListByIdReq dto);

    CreatePageTypeRes create(CreatePageTypeReq dto);

    EditPageTypeRes edit(EditPageTypeReq dto);

    void delete(DeletePageTypeReq dto);
}
