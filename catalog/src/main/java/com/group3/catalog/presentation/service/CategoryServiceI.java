package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.category.request.*;
import com.group3.catalog.domain.dto.category.response.*;

public interface CategoryServiceI {

    GetAllCategoryRes getAll();

    GetCategoryByIdRes getById(GetCategoryByIdReq dto);

    GetCategoryListByIdRes getListById(GetCategoryListByIdReq dto);

    CreateCategoryRes create(CreateCategoryReq dto);

    EditCategoryRes edit(EditCategoryReq dto);

    void delete(DeleteCategoryReq dto);
}
