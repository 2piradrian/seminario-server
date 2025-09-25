package com.group3.profiles.domain.dto.profile.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.profiles.domain.dto.profile.request.EditUserProfileReq;
import com.group3.profiles.domain.dto.profile.response.EditUserProfileRes;

import java.util.List;
import java.util.Map;

public class EditMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EditUserProfileReq toRequest(String token, Map<String, Object> payload){
        return EditUserProfileReq.create(
            token,
            (String) payload.get("name"),
            (String) payload.get("surname"),
            (String) payload.get("portraitImage"),
            (String) payload.get("profileImage"),
            (String) payload.get("shortDescription"),
            (String) payload.get("longDescription"),
            objectMapper.convertValue(payload.get("styles"), new TypeReference<List<Style>>() {}),
            objectMapper.convertValue(payload.get("instruments"), new TypeReference<List<Instrument>>() {})
        );
    }

    public EditUserProfileRes toResponse(UserProfile userProfile) {
        return new EditUserProfileRes(
            userProfile.getId()
        );
    }

}
