package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.category.request.GetCategoryByIdReq;
import com.group3.catalog.domain.dto.category.request.GetCategoryListByIdReq;
import com.group3.catalog.domain.dto.category.response.GetAllCategoryRes;
import com.group3.catalog.domain.dto.category.response.GetCategoryByIdRes;
import com.group3.catalog.domain.dto.category.response.GetCategoryListByIdRes;

public interface CategoryServiceI {

    GetAllCategoryRes getAll();

    GetCategoryByIdRes getById(GetCategoryByIdReq dto);

    GetCategoryListByIdRes getListById(GetCategoryListByIdReq dto);
}
