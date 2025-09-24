package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.GetUserByIdReq;
import com.group3.users.domain.dto.user.response.GetUserByIdRes;

public class GetByIdMapper {

    public GetUserByIdReq toRequest(String userId) {
        return GetUserByIdReq.create(
            userId
        );
    }

    public GetUserByIdRes toResponse(User user) {
        return new GetUserByIdRes(
            user.getId(),
            user.getName(),
            user.getSurname(),
            user.getEmail(),
            user.getMemberSince(),
            user.getLastLogin(),
            user.getPortraitImage(),
            user.getProfileImage(),
            user.getShortDescription(),
            user.getLongDescription(),
            user.getStyles(),
            user.getInstruments()
        );
    }

}
