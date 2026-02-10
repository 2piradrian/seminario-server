package com.group3.page_profiles.presentation.service;

import com.group3.page_profiles.domain.dto.request.*;
import com.group3.page_profiles.domain.dto.response.*;

public interface PageProfileServiceI {

    CreatePageRes create(CreatePageReq dto);

    GetPageByIdRes getById(GetPageByIdReq dto);

    GetPageByUserIdRes getUserPages(GetPageByUserIdReq dto);

    GetPageProfilePageFilteredRes getProfileFiltered(GetPageProfilePageFilteredReq dto);

    GetPageListByIdsRes getListByIds(GetPageListByIdsReq dto);

    void edit(EditPageReq dto);

    void joinPage(JoinPageReq dto);

    void leavePage(LeavePageReq dto);

    void delete(DeletePageReq dto);

    void deleteUserPages(DeleteUserPagesReq dto);

    GetPageGrowthReportRes getGrowthReport(GetPageGrowthReportReq dto);

}
