package com.group3.page_profiles.presentation.service;

import com.group3.page_profiles.domain.dto.request.*;
import com.group3.page_profiles.domain.dto.response.CreatePageRes;
import com.group3.page_profiles.domain.dto.response.GetPageByIdRes;
import com.group3.page_profiles.domain.dto.response.GetPageByUserIdRes;
import com.group3.page_profiles.domain.dto.response.GetPageListByIdsRes;

public interface PageProfileServiceI {

    CreatePageRes create(CreatePageReq dto);

    GetPageByIdRes getById(GetPageByIdReq dto);

    GetPageByUserIdRes getUserPages(GetPageByUserIdReq dto);

    GetPageListByIdsRes getListByIds(GetPageListByIdsReq dto);

    void edit(EditPageReq dto);

    void delete(DeletePageReq dto);

}
