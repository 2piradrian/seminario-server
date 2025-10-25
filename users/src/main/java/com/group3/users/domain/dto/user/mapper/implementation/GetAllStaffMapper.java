package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.GetAllStaffReq;
import com.group3.users.domain.dto.user.request.GetUserByIdReq;
import com.group3.users.domain.dto.user.response.GetAllStaffRes;

import java.util.List;

public class GetAllStaffMapper {

    public GetAllStaffReq toRequest(String token) {
        return GetAllStaffReq.create(
            token
        );
    }

    public GetAllStaffRes toResponse(List<User> users) {
        return new GetAllStaffRes(users);
    }
}
