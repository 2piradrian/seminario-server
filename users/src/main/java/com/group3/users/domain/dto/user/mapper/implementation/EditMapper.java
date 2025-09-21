package com.group3.users.domain.dto.user.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.EditUserReq;
import com.group3.users.domain.dto.user.response.EditUserRes;

import java.util.List;
import java.util.Map;

public class EditMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EditUserReq toRequest(String token, Map<String, Object> payload){
        return EditUserReq.create(
            token,
            (String) payload.get("profileId"),
            (String) payload.get("name"),
            (String) payload.get("surname"),
            (String) payload.get("portraitImage"),
            (String) payload.get("profileImage"),
            (String) payload.get("shortDescription"),
            (String) payload.get("longDescription"),
            objectMapper.convertValue(payload.get("styles"), new TypeReference<List<String>>() {}),
            objectMapper.convertValue(payload.get("instruments"), new TypeReference<List<String>>() {})
        );
    }

    public EditUserRes toResponse(User userProfile) {
        return new EditUserRes(
            userProfile.getId()
        );
    }

}
