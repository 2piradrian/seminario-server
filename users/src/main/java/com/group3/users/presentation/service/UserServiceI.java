package com.group3.users.presentation.service;

import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.*;

public interface UserServiceI {

    GetUserByIdRes getById(GetUserByIdReq dto);

    GetUserMutualsFollowersRes getMutualsFollowers(GetUserMutualsFollowersReq dto);

    void delete(DeleteUserReq dto);

    void deleteById(String token, String id);

    GetAllStaffRes getAllStaff(GetAllStaffReq dto);

    GetUserPageFilteredRes getProfileFiltered(GetUserPageFilteredReq dto);

    GetByListOfIdsPageRes getByListOfIds(GetByListOfIdsPageReq dto);

    void update(EditUserReq dto);

    GetUserGrowthReportRes getGrowthReport(GetUserGrowthReportReq dto);

}
