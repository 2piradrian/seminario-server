package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.Role;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.users.domain.dto.user.request.GetAllStaffReq;
import com.group3.users.domain.dto.user.request.GetUserByIdReq;
import com.group3.users.domain.dto.user.response.GetAllStaffRes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllStaffMapper {

    public GetAllStaffReq toRequest(String token) {
        return GetAllStaffReq.create(
            token
        );
    }

    public GetAllStaffRes toResponse(List<User> staff) {
        return new GetAllStaffRes(staff);
    }
}
