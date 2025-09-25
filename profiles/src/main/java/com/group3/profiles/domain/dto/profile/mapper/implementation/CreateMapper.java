package com.group3.profiles.domain.dto.profile.mapper.implementation;

import com.group3.profiles.domain.dto.profile.request.CreateUserProfileReq;

import java.util.Map;

public class CreateMapper {

    public CreateUserProfileReq toRequest(Map<String, Object> payload){
        return CreateUserProfileReq.create(
                (String) payload.get("id"),
                (String) payload.get("email"),
                (String) payload.get("name"),
                (String) payload.get("surname")
        );
    }

}
