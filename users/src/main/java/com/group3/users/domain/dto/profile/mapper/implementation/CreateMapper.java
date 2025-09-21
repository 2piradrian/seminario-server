package com.group3.users.domain.dto.profile.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.users.domain.dto.profile.request.EditUserProfileReq;
import com.group3.users.domain.dto.profile.response.EditUserProfileRes;

import java.util.Map;
import java.util.Set;

public class CreateMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EditUserProfileReq toRequest(Map<String, Object> payload){
        return EditUserProfileReq.create(
            (String) payload.get("portraitImage"),
            (String) payload.get("profileImage"),
            (String) payload.get("shortDescription"),
            (String) payload.get("longDescription"),
            objectMapper.convertValue(payload.get("styles"), new TypeReference<Set<Style>>() {}),
            objectMapper.convertValue(payload.get("instruments"), new TypeReference<Set<Instrument>>() {})
        );
    }

    public EditUserProfileRes toResponse(UserProfile userProfile) {
        return new EditUserProfileRes(
            userProfile.getId(),
            userProfile.getPortraitImage(),
            userProfile.getProfileImage(),
            userProfile.getShortDescription(),
            userProfile.getLongDescription(),
            userProfile.getStyles(),
            userProfile.getInstruments()

        );
    }

}
