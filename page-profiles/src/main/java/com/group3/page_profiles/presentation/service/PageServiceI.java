package com.group3.page_profiles.presentation.service;

import com.group3.page_profiles.domain.dto.request.*;
import com.group3.page_profiles.domain.dto.response.CreatePageRes;
import com.group3.page_profiles.domain.dto.response.GetPageByIdRes;
import com.group3.page_profiles.domain.dto.response.GetPageByUserIdRes;

public interface PageServiceI {

    CreatePageRes create(CreatePageReq dto);

    GetPageByIdRes getById(GetPageByIdReq dto);

    GetPageByUserIdRes getUserPages(GetPageByUserIdReq dto);

    void edit(EditPageReq dto);

    void delete(DeletePageReq dto);

}
