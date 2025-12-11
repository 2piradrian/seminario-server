package com.group3.users.presentation.service;

import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.*;

public interface UserServiceI {

    GetUserByIdRes getById(GetUserByIdReq dto);

    GetUserMutualsFollowersRes getMutualsFollowers(GetUserMutualsFollowersReq dto);

    void delete(DeleteUserReq dto);

    GetAllStaffRes getAllStaff(GetAllStaffReq dto);

    GetUserPageFilteredRes getProfileFiltered(GetUserPageFilteredReq dto);

    void update(EditUserReq dto);

}
