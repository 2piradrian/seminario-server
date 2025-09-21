package com.group3.users.domain.dto.profile.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.users.domain.dto.profile.request.CreateUserProfileReq;
import com.group3.users.domain.dto.profile.response.CreateUserProfileRes;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CreateUserProfileReq toRequest(Map<String, Object> payload){
        return CreateUserProfileReq.create(
            (String) payload.get("portraitImage"),
            (String) payload.get("profileImage"),
            (String) payload.get("shortDescription"),
            (String) payload.get("longDescription"),
            objectMapper.convertValue(payload.get("styles"), new TypeReference<Set<Style>>() {}),
            objectMapper.convertValue(payload.get("instruments"), new TypeReference<Set<Instrument>>() {})
        );
    }

  public CreateUserProfileRes toResponse(UserProfile userProfile) {
        return new CreateUserProfileRes(
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
