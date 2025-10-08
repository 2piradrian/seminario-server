package com.group3.pages.presentation.service;

import com.group3.pages.domain.dto.request.*;
import com.group3.pages.domain.dto.response.CreatePageRes;
import com.group3.pages.domain.dto.response.GetPageByIdRes;
import com.group3.pages.domain.dto.response.GetPageByUserIdRes;

public interface PageServiceI {

    CreatePageRes create(CreatePageReq dto);

    GetPageByIdRes getById(GetPageByIdReq dto);

    GetPageByUserIdRes getUserPages(GetPageByUserIdReq dto);

    void edit(EditPageReq dto);

    void delete(DeletePageReq dto);

}
