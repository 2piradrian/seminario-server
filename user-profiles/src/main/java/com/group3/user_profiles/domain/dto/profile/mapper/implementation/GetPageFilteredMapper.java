package com.group3.user_profiles.domain.dto.profile.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Instrument;
import com.group3.entity.PageContent;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.user_profiles.domain.dto.profile.request.GetUserProfilePageFilteredReq;
import com.group3.user_profiles.domain.dto.profile.response.GetUserProfilePageFilteredRes;

import java.util.List;
import java.util.Map;

public class GetPageFilteredMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetUserProfilePageFilteredReq toRequest(Map<String, Object> payload) {
        return GetUserProfilePageFilteredReq.create(
            (String) payload.get("secret"),
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            (String) payload.get("fullname"),
            objectMapper.convertValue(payload.get("styles"), new TypeReference<List<Style>>() {}),
            objectMapper.convertValue(payload.get("instruments"), new TypeReference<List<Instrument>>() {}),
            objectMapper.convertValue(payload.get("ids"), new TypeReference<List<String>>() {})

        );
    }

    public GetUserProfilePageFilteredRes toResponse(PageContent<UserProfile> profiles) {
        return new GetUserProfilePageFilteredRes(
            profiles.getContent(),
            profiles.getNextPage()
        );
    }

}
