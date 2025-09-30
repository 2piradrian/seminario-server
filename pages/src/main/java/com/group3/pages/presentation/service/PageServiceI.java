package com.group3.pages.presentation.service;

import com.group3.pages.domain.dto.request.*;

public interface PageServiceI {

    void create(CreatePageReq dto);

    GetPageByIdReq getById(GetPageByIdReq dto);

    GetPageByUserIdReq getUserPages(GetPageByUserIdReq dto);

    void update(EditPageReq dto);

    void delete(DeletePageReq dto);

}
