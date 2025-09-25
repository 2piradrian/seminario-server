package com.group3.profiles.domain.dto.user.mapper.implementation;

import com.group3.entity.User;
import com.group3.profiles.domain.dto.user.request.GetOwnProfileReq;
import com.group3.profiles.domain.dto.user.response.GetOwnProfileRes;

public class GetOwnProfileMapper {

    public GetOwnProfileReq toRequest(String token) {
        return GetOwnProfileReq.create(
            token
        );
    }

    public GetOwnProfileRes toResponse(User user) {
        return new GetOwnProfileRes(
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
